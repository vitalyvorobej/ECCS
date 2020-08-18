package com.itacademy.jd2.vv.cec.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vv.cec.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;
import com.itacademy.jd2.vv.cec.service.IUserAccountService;
import com.itacademy.jd2.vv.cec.web.converter.UserAccountFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.UserAccountToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.UserAccountDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.UserAccountSearchDTO;

@Controller
@RequestMapping(value = "/user")
public class UserAccountController extends AbstractController<UserAccountDTO, UserAccountFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = UserAccountController.class.getSimpleName() + "_SEARCH_DTO";
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private UserAccountFromDtoConverter fromDtoConverter;
	@Autowired
	private UserAccountToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) UserAccountSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final ListDTO<UserAccountDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equals("get")) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}
		final UserAccountFilter filter = new UserAccountFilter();
		if (searchDto.getEmail() != null) {
			filter.setEmail(searchDto.getEmail());
		}
		listDTO.setTotalCount(userAccountService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);
		final List<IUserAccount> entities = userAccountService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);
		return new ModelAndView("user.list", models);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) throws Exception {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formodel", formModel);
			return new ModelAndView("user", hashMap);
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			userAccountService.save(entity);
			return "redirect:/user"; // may be bag
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/user";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.get(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("user.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final HashMap<String, Object> hashMap = new HashMap<>();
		final UserAccountDTO dto = new UserAccountDTO();
		hashMap.put("formModel", dto);
		return new ModelAndView("user.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		return new ModelAndView("user.edit", hashMap);
	}

	private UserAccountSearchDTO getSearchDTO(HttpServletRequest req) {
		UserAccountSearchDTO searchDTO = (UserAccountSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new UserAccountSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
}
