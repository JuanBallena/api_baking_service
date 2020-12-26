package com.jp.baking.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.BakeTicketConverter;
import com.jp.baking.service.dto.bakeTicket.BakeTicketDto;
import com.jp.baking.service.dto.bakeTicket.CreateBakeTicketAndCustomerDto;
import com.jp.baking.service.dto.bakeTicket.CreateBakeTicketDto;
import com.jp.baking.service.dto.bakeTicket.UpdateBakeTicketBakingStatusDto;
import com.jp.baking.service.dto.bakeTicket.UpdateBakeTicketCustomerDto;
import com.jp.baking.service.dto.bakeTicket.UpdateBakeTicketDto;
import com.jp.baking.service.model.Activity;
import com.jp.baking.service.model.BakeTicket;
import com.jp.baking.service.model.Customer;
import com.jp.baking.service.model.Parameter;
import com.jp.baking.service.model.PlaceAttention;
import com.jp.baking.service.repository.BakeTicketRepository;
import com.jp.baking.service.repository.CustomerRepository;
import com.jp.baking.service.requestParameter.BakeTicketRequest;
import com.jp.baking.service.requestParameter.SearchBakeTicketRequest;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.validator.Validator;

@Service
public class BakeTicketManager {
	
	private Validator validator = new Validator();
	
	@Autowired
	private BakeTicketRepository bakeTicketRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BakeTicketConverter bakeTicketConverter;

	public ListResponse findAll(BakeTicketRequest request) {
		
		if (request.hasFilterAndPagingAndSortParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters(), request.getPagingAndSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasFilterAndPagingParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters(), request.getPaging());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasFilterAndSortParameters()) {
			List<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters(), request.getSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasPagingAndSortParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getPagingAndSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasFilterParameters()) {
			List<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters());
			return this.toListResponse(bakeTicketPage);
		}

		if (request.hasPagingParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getPaging());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasSortParameters()) {
			List<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		List<BakeTicket> bakeTicketList = bakeTicketRepository.findAll();
		return this.toListResponse(bakeTicketList);
	}
	
	public ListResponse search(SearchBakeTicketRequest request) {
		
		if (request.hasFilterAndPagingAndSortParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters(), request.getPagingAndSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasFilterAndPagingParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters(), request.getPaging());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasFilterAndSortParameters()) {
			List<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters(), request.getSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasPagingAndSortParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getPagingAndSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasFilterParameters()) {
			List<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getFilters());
			return this.toListResponse(bakeTicketPage);
		}

		if (request.hasPagingParameters()) {
			Page<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getPaging());
			return this.toListResponse(bakeTicketPage);
		}
		
		if (request.hasSortParameters()) {
			List<BakeTicket> bakeTicketPage = bakeTicketRepository.findAll(request.getSort());
			return this.toListResponse(bakeTicketPage);
		}
		
		List<BakeTicket> bakeTicketList = bakeTicketRepository.findAll();
		return this.toListResponse(bakeTicketList);
	}
	
	private ListResponse toListResponse(Page<BakeTicket> bakeTicketPage) {
		
		List<BakeTicketDto> list = bakeTicketConverter.toBakeTicketDtoList(bakeTicketPage.getContent());
		return ListResponse.builder()
				.list(list)
				.totalPages(bakeTicketPage.getTotalPages())
				.build();
	}
	
	private ListResponse toListResponse(List<BakeTicket> bakeTicketList) {
		
		List<BakeTicketDto> list = bakeTicketConverter.toBakeTicketDtoList(bakeTicketList);
		return ListResponse.builder()
				.list(list)
				.totalPages(list.size() == 0 ? 0 : 1)
				.build();
	}
	
	public BakeTicketDto findById(Long idBakeTicket) {
		
		if (validator.notPositiveNumber(idBakeTicket)) return null;
		
		BakeTicket bakeTicket = bakeTicketRepository.findByIdBakeTicket(idBakeTicket);
		
		if (validator.isNull(bakeTicket)) return null;
		
		return bakeTicketConverter.toBakeTicketDto(bakeTicket);
	}
	
	private String replaceNumberAttention(String numberAttention) {
		if (numberAttention.length() == 1) return "00" + numberAttention;
		if (numberAttention.length() == 2) return "0" + numberAttention;
		return numberAttention;
	}
	
	public BakeTicketDto save(CreateBakeTicketDto dto) {
			
		BakeTicket bakeTicket = new BakeTicket();
		bakeTicket.setCustomer(Customer.builder().idCustomer(dto.getIdCustomer()).build());
		bakeTicket.setActivity(Activity.builder().idActivity(dto.getIdActivity()).build());
		bakeTicket.setPlaceAttention(PlaceAttention.builder().idPlaceAttention(dto.getIdPlaceAttention()).build());
		bakeTicket.setNumberAttention(this.replaceNumberAttention(dto.getNumberAttention()));
		bakeTicketRepository.save(bakeTicket);
		bakeTicketRepository.refresh(bakeTicket);
		
		return bakeTicketConverter.toBakeTicketDto(bakeTicket);
	}
	
	public BakeTicketDto save(CreateBakeTicketAndCustomerDto dto) {
		
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setDocument(dto.getDocument());
		customer.setPhone(dto.getPhone());
		customerRepository.save(customer);
		customerRepository.refresh(customer);
		
		BakeTicket bakeTicket = new BakeTicket();
		bakeTicket.setCustomer(customer);
		bakeTicket.setActivity(Activity.builder().idActivity(dto.getIdActivity()).build());
		bakeTicket.setPlaceAttention(PlaceAttention.builder().idPlaceAttention(dto.getIdPlaceAttention()).build());
		bakeTicket.setNumberAttention(this.replaceNumberAttention(dto.getNumberAttention()));
		bakeTicketRepository.save(bakeTicket);
		bakeTicketRepository.refresh(bakeTicket);
		
		return bakeTicketConverter.toBakeTicketDto(bakeTicket);
	}
	
	public BakeTicketDto update(UpdateBakeTicketDto dto) {
		
		if (validator.notPositiveNumber(dto.getIdBakeTicket())) return null;
		
		BakeTicket bakeTicket = bakeTicketRepository.findByIdBakeTicket(dto.getIdBakeTicket());
		
		if (validator.isNull(bakeTicket)) return null;
		
		bakeTicket.setCustomer(Customer.builder().idCustomer(dto.getIdCustomer()).build());
		bakeTicket.setActivity(Activity.builder().idActivity(dto.getIdActivity()).build());
		bakeTicket.setPlaceAttention(PlaceAttention.builder().idPlaceAttention(dto.getIdPlaceAttention()).build());
		bakeTicket.setNumberAttention(this.replaceNumberAttention(dto.getNumberAttention()));
		bakeTicketRepository.save(bakeTicket);
		bakeTicketRepository.refresh(bakeTicket);
		
		return bakeTicketConverter.toBakeTicketDto(bakeTicket);
	}
	
	public BakeTicketDto update(UpdateBakeTicketCustomerDto dto) {
		
		if (validator.notPositiveNumber(dto.getIdBakeTicket())) return null;
		
		BakeTicket bakeTicket = bakeTicketRepository.findByIdBakeTicket(dto.getIdBakeTicket());
		
		if (validator.isNull(bakeTicket)) return null;
		
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setDocument(dto.getDocument());
		customer.setPhone(dto.getPhone());
		customerRepository.save(customer);
		customerRepository.refresh(customer);
		
		bakeTicket.setCustomer(customer);
		bakeTicketRepository.save(bakeTicket);
		bakeTicketRepository.refresh(bakeTicket);
		
		return bakeTicketConverter.toBakeTicketDto(bakeTicket);
	}
	
	public BakeTicketDto update(UpdateBakeTicketBakingStatusDto dto) {
		
		if (validator.notPositiveNumber(dto.getIdBakeTicket())) return null;
		
		BakeTicket bakeTicket = bakeTicketRepository.findByIdBakeTicket(dto.getIdBakeTicket());
		
		if (validator.isNull(bakeTicket)) return null;
		
		bakeTicket.setBakingStatus(Parameter.builder().idParameter(dto.getIdBakingStatus()).build());
		bakeTicketRepository.save(bakeTicket);
		bakeTicketRepository.refresh(bakeTicket);
		
		return bakeTicketConverter.toBakeTicketDto(bakeTicket);
	}
	
	public boolean attentionNumberNotExist(String numberAttention, Long idActivity, Long idPlaceAttention) {
		
		BakeTicket bakeTicket = bakeTicketRepository.findByNumberAttentionAndActivityAndPlaceAttention(
				this.replaceNumberAttention(numberAttention), 
				Activity.builder().idActivity(idActivity).build(), 
				PlaceAttention.builder().idPlaceAttention(idPlaceAttention).build());
		
		return validator.isNull(bakeTicket);
	}
	
	public boolean attentionNumberNotExist(String numberAttention, Long idActivity, Long idPlaceAttention, Long id) {
		
		BakeTicket bakeTicket = bakeTicketRepository.findByNumberAttentionAndActivityAndPlaceAttention(
				this.replaceNumberAttention(numberAttention), 
				Activity.builder().idActivity(idActivity).build(), 
				PlaceAttention.builder().idPlaceAttention(idPlaceAttention).build());
		
		if (validator.notNull(bakeTicket)) return bakeTicket.getIdBakeTicket().equals(id);
		
		return true;
	}
}
