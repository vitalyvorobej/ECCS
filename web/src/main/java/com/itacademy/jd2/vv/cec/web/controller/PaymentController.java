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

import com.itacademy.jd2.vv.cec.dao.api.filter.PaymentFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;
import com.itacademy.jd2.vv.cec.service.IOrderObjectService;
import com.itacademy.jd2.vv.cec.service.IPaymentService;
import com.itacademy.jd2.vv.cec.web.converter.PaymentFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.PaymentToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.PaymentDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.PaymentSearchDTO;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController extends AbstractController<PaymentDTO, PaymentFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = PaymentController.class.getSimpleName() + "_SEARCH_DTO";

	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private IOrderObjectService orderService;
	@Autowired
	private PaymentFromDtoConverter fromDtoConverter;
	@Autowired
	private PaymentToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) PaymentSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final ListDTO<PaymentDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equalsIgnoreCase("get")) {
			// do not use empty payload which comes in case of GET
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}
		final PaymentFilter filter = new PaymentFilter();// возможен баг
		if (searchDto.getOrderId() != null) {
			filter.setOrderId(searchDto.getOrderId());
		}
		listDTO.setTotalCount(paymentService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);
		final List<IPayment> entities = paymentService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);
		/*
		 * final ICard newestCard = cardService.getNewestCard(); models.put("newestCardId", newestCard == null ? null : newestCard.getId());
		 */
		return new ModelAndView("payment.list", models);
	}

	private PaymentSearchDTO getSearchDTO(HttpServletRequest req) {
		PaymentSearchDTO searchDTO = (PaymentSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new PaymentSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);

		}
		return searchDTO;

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final HashMap<String, Object> hashMap = new HashMap<>();
		final PaymentDTO dto = new PaymentDTO();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("payment.edit", hashMap);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PaymentDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("payment.edit", hashMap);
		} else {
			final IPayment entity = fromDtoConverter.apply(formModel);
			paymentService.save(entity);
			return "redirect:/payment";
		}

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		paymentService.delete(id);
		return "redirect:/payment";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPayment dbModel = paymentService.get(id);
		final PaymentDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("payment.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PaymentDTO dto = toDtoConverter.apply(paymentService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("payment.edit", hashMap);
	}

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final Map<Integer, Integer> orderMap = orderService.getAll().stream()
				.collect(Collectors.toMap(IOrderObject::getId, IOrderObject::getId));
		hashMap.put("paymentChoices", orderMap);
	}
}
