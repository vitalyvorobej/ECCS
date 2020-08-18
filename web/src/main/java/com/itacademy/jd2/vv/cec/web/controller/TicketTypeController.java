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

import com.itacademy.jd2.vv.cec.dao.api.filter.TicketTypeFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.service.ITicketTypeService;
import com.itacademy.jd2.vv.cec.web.converter.TicketTypeFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.TicketTypeToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.TicketTypeDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.TicketTypeSearchDTO;

@Controller
@RequestMapping(value = "/ticket")
public class TicketTypeController extends AbstractController<TicketTypeDTO, TicketTypeFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = TicketTypeController.class.getSimpleName() + "_SEARCH_DTO";
	@Autowired
	private ITicketTypeService ticketTypeService;
	@Autowired
	private TicketTypeFromDtoConverter fromDtoConverter;
	@Autowired
	private TicketTypeToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) TicketTypeSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final ListDTO<TicketTypeDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equalsIgnoreCase("get")) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final TicketTypeFilter filter = new TicketTypeFilter();
		if (searchDto.getName() != null) {
			filter.setName(searchDto.getName());
		}
		listDTO.setTotalCount(ticketTypeService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);

		final List<ITicketType> entities = ticketTypeService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);

		return new ModelAndView("ticket.list", models);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TicketTypeDTO formModel, final BindingResult result) throws Exception {

		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formodel", formModel);
			return new ModelAndView("ticket.edit", hashMap);
		} else {
			final ITicketType entity = fromDtoConverter.apply(formModel);
			ticketTypeService.save(entity);
			return "redirect:/ticket";
		}

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		ticketTypeService.delete(id);
		return "redirect:/ticket";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITicketType dbModel = ticketTypeService.get(id);
		final TicketTypeDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {

		final HashMap<String, Object> hashMap = new HashMap<>();
		final TicketTypeDTO dto = new TicketTypeDTO();
		hashMap.put("formModel", dto);
		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TicketTypeDTO dto = toDtoConverter.apply(ticketTypeService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("ticket.edit", hashMap);
	}

	private TicketTypeSearchDTO getSearchDTO(HttpServletRequest req) {
		TicketTypeSearchDTO searchDTO = (TicketTypeSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new TicketTypeSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}

}
