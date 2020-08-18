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

import com.itacademy.jd2.vv.cec.dao.api.filter.CardFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.service.ICardService;
import com.itacademy.jd2.vv.cec.service.IClientService;
import com.itacademy.jd2.vv.cec.web.converter.CardFromDtoConverter;
import com.itacademy.jd2.vv.cec.web.converter.CardToDtoConverter;
import com.itacademy.jd2.vv.cec.web.dto.CardDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.search.CardSearchDTO;

@Controller
@RequestMapping(value = "/card")
public class CardController extends AbstractController<CardDTO, CardFilter> {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = CardController.class.getSimpleName() + "_SEARCH_DTO";
	@Autowired
	private ICardService cardService;
	@Autowired
	private IClientService clientService;
	@Autowired
	private CardFromDtoConverter fromDtoConverter;
	@Autowired
	private CardToDtoConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) CardSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final ListDTO<CardDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);
		if (req.getMethod().equalsIgnoreCase("get")) {
			// do not use empty payload which comes in case of GET
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}
		final CardFilter filter = new CardFilter();// возможен баг
		if (searchDto.getClientId() != null) {
			filter.setClientId(searchDto.getClientId());
		}
		listDTO.setTotalCount(cardService.getCount(filter));
		applySortAndOrder2Filter(listDTO, filter);
		final List<ICard> entities = cardService.find(filter);
		listDTO.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.SESSION_ATTR_NAME, listDTO);
		models.put(SEARCH_FORM_MODEL, searchDto);
		/*
		 * final ICard newestCard = cardService.getNewestCard(); models.put("newestCardId", newestCard == null ? null : newestCard.getId());
		 */
		return new ModelAndView("card.list", models);

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new CardDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("card.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CardDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("card.edit", hashMap);
		} else {
			final ICard entity = fromDtoConverter.apply(formModel);
			cardService.save(entity);
			return "redirect:/card";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viedDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICard dbModel = cardService.get(id);
		final CardDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("card.edit", hashMap);

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CardDTO dto = toDtoConverter.apply(cardService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("card.edit", hashMap);

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		cardService.delete(id);
		return "redirect:/card";
	}

	/*
	 * @RequestMapping(value = "/lastId", method = RequestMethod.GET) public ResponseEntity<Integer> getNewesCard() { final ICard newesCard
	 * = cardService.getNewestCard(); return new ResponseEntity<Integer>(newesCard == null ? null : newesCard.getId(), HttpStatus.OK); }
	 */

	private void loadCommonFormModels(Map<String, Object> hashMap) {
		final Map<Integer, String> clientMap = clientService.getAll().stream()
				.collect(Collectors.toMap(IClient::getId, IClient::getLastName));

		hashMap.put("clientChoices", clientMap);
		// комбик тут
	}

	private CardSearchDTO getSearchDTO(final HttpServletRequest req) {
		CardSearchDTO searchDTO = (CardSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new CardSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);

		}
		return searchDTO;
	}

}
