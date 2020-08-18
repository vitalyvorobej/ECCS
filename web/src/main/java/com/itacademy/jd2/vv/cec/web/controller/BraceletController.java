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

import com.itacademy.jd2.vv.cec.dao.api.filter.BraceletFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.service.IBraceletService;
import com.itacademy.jd2.vv.cec.web.converter.BraceletFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.BraceletToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.BraceletDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.BraceletSearchDTO;

@Controller
@RequestMapping(value = "/bracelet")
public class BraceletController extends AbstractController<BraceletDTO, BraceletFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = BraceletController.class.getSimpleName() + "_SEARCH_DTO";
	@Autowired
	private IBraceletService braceletService;
	@Autowired
	private BraceletFromDtoConverter fromDtoConverter;

	@Autowired
	private BraceletToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) BraceletSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		// final Integer loggedUserId = AuthHelper.getLoggedUserId();

		final ListDTO<BraceletDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equalsIgnoreCase("get")) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final BraceletFilter filter = new BraceletFilter();
		if (searchDto.getUuid() != null) {
			filter.setUuid(searchDto.getUuid());
		}
		listDTO.setTotalCount(braceletService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);
		final List<IBracelet> entities = braceletService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);
		return new ModelAndView("bracelet.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final BraceletDTO formModel, final BindingResult result) throws Exception {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formodel", formModel);
			return new ModelAndView("bracelet.edit", hashMap);
		} else {
			final IBracelet entity = fromDtoConverter.apply(formModel);
			braceletService.save(entity);
			return "redirect:/bracelet";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		braceletService.delete(id);
		return "redirect:/bracelet";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IBracelet dbModel = braceletService.get(id);
		final BraceletDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("bracelet.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {

		final HashMap<String, Object> hashMap = new HashMap<>();
		final BraceletDTO dto = new BraceletDTO();
		hashMap.put("formModel", dto);
		return new ModelAndView("bracelet.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final BraceletDTO dto = toDtoConverter.apply(braceletService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("bracelet.edit", hashMap);
	}

	private BraceletSearchDTO getSearchDTO(final HttpServletRequest req) {
		BraceletSearchDTO searchDTO = (BraceletSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new BraceletSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}

}
