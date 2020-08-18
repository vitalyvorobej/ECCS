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

import com.itacademy.jd2.vv.cec.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.service.IClientService;
import com.itacademy.jd2.vv.cec.web.converter.ClientFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.ClientToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.ClientDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.ClientSearchDTO;

@Controller
@RequestMapping(value = "/client")
public class ClientController extends AbstractController<ClientDTO, ClientFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = ClientController.class.getSimpleName() + "_SEARCH_DTO";
	@Autowired
	private IClientService clientService;
	@Autowired
	private ClientFromDtoConverter fromDtoConverter;

	@Autowired
	private ClientToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) ClientSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final ListDTO<ClientDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equals("get")) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}
		final ClientFilter filter = new ClientFilter();
		if (searchDto.getLast_name() != null) {
			filter.setLast_name(searchDto.getLast_name());
		}
		listDTO.setTotalCount(clientService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);
		final List<IClient> entities = clientService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);
		return new ModelAndView("client.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ClientDTO formModel, final BindingResult result) throws Exception {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formodel", formModel);
			return new ModelAndView("client.edit", hashMap);
		} else {
			final IClient entity = fromDtoConverter.apply(formModel);
			clientService.save(entity);
			return "redirect:/client";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		clientService.delete(id);
		return "redirect:/client";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IClient dbModel = clientService.get(id);
		final ClientDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("client.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final HashMap<String, Object> hashMap = new HashMap<>();
		final ClientDTO dto = new ClientDTO();
		hashMap.put("formModel", dto);
		return new ModelAndView("client.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ClientDTO dto = toDtoConverter.apply(clientService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("client.edit", hashMap);
	}

	private ClientSearchDTO getSearchDTO(HttpServletRequest req) {
		ClientSearchDTO searchDTO = (ClientSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new ClientSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
	// допилить метод modelsChoices
}
