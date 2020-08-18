package com.itacademy.jd2.vv.cec.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vv.cec.dao.api.filter.OrderObjectFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.service.IBraceletService;
import com.itacademy.jd2.vv.cec.service.ICardService;
import com.itacademy.jd2.vv.cec.service.IOrderObjectService;
import com.itacademy.jd2.vv.cec.service.ITicketTypeService;
import com.itacademy.jd2.vv.cec.web.converter.OrderObjectFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.OrderObjectToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.OrderObjectDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.OrderObjectSearchDTO;

@Controller
@RequestMapping(value = "/order")
public class OrderObjectController extends AbstractController<OrderObjectDTO, OrderObjectFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = OrderObjectController.class.getSimpleName() + "_SEARCH_DTO";

	@Autowired
	private IOrderObjectService orderService;
	@Autowired
	private ICardService cardService;

	@Autowired
	private ITicketTypeService ticketService;
	@Autowired
	private IBraceletService braceletService;

	@Autowired
	private OrderObjectFromDtoConverter fromDtoConverter;

	@Autowired
	private OrderObjectToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) OrderObjectSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final ListDTO<OrderObjectDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equalsIgnoreCase("get")) {
			// do not use empty payload which comes in case of GET
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}
		final OrderObjectFilter filter = new OrderObjectFilter();
		if (searchDto.getCardId() != null) {
			filter.setCardId(searchDto.getCardId());
		}
		listDTO.setTotalCount(orderService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);
		final List<IOrderObject> entities = orderService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);
		final IOrderObject newestOrder = orderService.getNewesOrder();
		models.put("newestOrderId", newestOrder == null ? null : newestOrder.getId());
		return new ModelAndView("order.list", models);

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new OrderObjectDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("order.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final OrderObjectDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("order.edit", hashMap);
		} else {
			final IOrderObject entity = fromDtoConverter.apply(formModel);
			orderService.save(entity);
			return "redirect:/order";
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viedDetaild(@PathVariable(name = "id", required = true) final Integer id) {
		final IOrderObject dbModel = orderService.get(id);
		final OrderObjectDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("order.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final OrderObjectDTO dto = toDtoConverter.apply(orderService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("order.edit", hashMap);

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		orderService.delete(id);
		return "redirect:/order";
	}

	@RequestMapping(value = "/lastId", method = RequestMethod.GET)
	public ResponseEntity<Integer> getNewesOrder() {
		final IOrderObject newesOrder = orderService.getNewesOrder();
		return new ResponseEntity<Integer>(newesOrder == null ? null : newesOrder.getId(), HttpStatus.OK);
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		List<ICard> all = cardService.getAll();
		// all.stream().collect(Collectors.toMap(ICard::getId, ICard::getActive));// спросить

		final Map<Integer, String> braceletMap = braceletService.getAll().stream()
				.collect(Collectors.toMap(IBracelet::getId, IBracelet::getUuId));
		hashMap.put("braceletChoices", braceletMap);

		final Map<Integer, String> ticketTypeMap = ticketService.getAll().stream()
				.collect(Collectors.toMap(ITicketType::getId, ITicketType::getName));
		hashMap.put("ticketTypeChoices", ticketTypeMap);

		final Map<Integer, String> cardMap = new HashMap<>();

		for (ICard card : all) {
			cardMap.put(card.getId(), card.getClient().getLastName());
		}

		hashMap.put("cardChoices", cardMap);

	}

	private OrderObjectSearchDTO getSearchDTO(HttpServletRequest req) {
		OrderObjectSearchDTO searchDTO = (OrderObjectSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new OrderObjectSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
}
