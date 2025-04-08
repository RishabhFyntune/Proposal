package com.example.proposal.Service;

import java.util.List;
import java.util.Optional;

import com.example.proposal.Pagenation.ProposerPage;
import com.example.proposal.Pagenation.SearchFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proposal.DTO.ProposerDTO;
import com.example.proposal.Repository.ProposalRepo;
import com.example.proposal.model.Proposer;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepo proposalRepo;


    @Override

    public Proposer register(Proposer proposer) {
        proposer.setStatus('Y');

        return proposalRepo.save(proposer);
    }


    @Override
    public Proposer delete(Long id) throws Exception {
        Optional<Proposer> deleteProposer = proposalRepo.findById(id);
        Proposer updatedeletedstatus = deleteProposer.get();
        if (updatedeletedstatus.getStatus() == 'N') {
            throw new Exception("Proposer is deleted");

        }

        updatedeletedstatus.setStatus('N');
        return proposalRepo.save(updatedeletedstatus);


    }

    @Override
    public List<Proposer> getproposer()
    {
        return proposalRepo.findAll();
    }


    @Override
    public Proposer update(Long id, Proposer proposer) {
        Optional<Proposer> getpropose = proposalRepo.findById(id);

        Proposer oldproposer = getpropose.get();
        oldproposer.setAadharnumber(proposer.getAadharnumber());
        oldproposer.setAddress(proposer.getAddress());
        oldproposer.setAnnualincome(proposer.getAnnualincome());
        oldproposer.setCity(proposer.getCity());
        oldproposer.setDateOfBirth(proposer.getDateOfBirth());
        oldproposer.setEmail(proposer.getEmail());
        oldproposer.setGender(proposer.getGender());
        oldproposer.setId(proposer.getId());
        oldproposer.setMaritalstatus(proposer.getMaritalstatus());
        oldproposer.setName(proposer.getName());
        oldproposer.setPanNumber(proposer.getPanNumber());
        oldproposer.setPhonenumber(proposer.getPhonenumber());
        oldproposer.setPincode(proposer.getPincode());
        oldproposer.setState(proposer.getState());


        Proposer updatedProposer = proposalRepo.save(oldproposer);
        return
                updatedProposer;

    }

    @Override
    public Proposer saveproposerdto(ProposerDTO proposerDTO) {
        Proposer proposer = new Proposer();

        if (proposerDTO.getAadharnumber() == null || proposerDTO.getAadharnumber().length() != 12) {
            throw new IllegalArgumentException("Aadhaar number must be exactly 12 digits.");
        }
        if (proposalRepo.existsByAadharnumber(proposerDTO.getAadharnumber())) {
            throw new IllegalArgumentException("Aadhaar number already present");
        }

        proposer.setAadharnumber(proposerDTO.getAadharnumber());

        if (proposerDTO.getAddress() == null || proposerDTO.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        proposer.setAddress(proposerDTO.getAddress());

        if (proposerDTO.getAnnualincome() == null || proposerDTO.getAnnualincome().isEmpty()) {
            throw new IllegalArgumentException("Annual income must be a positive value.");
        }
        proposer.setAnnualincome(proposerDTO.getAnnualincome());

        if (proposerDTO.getCity() == null || proposerDTO.getCity().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty.");
        }
        proposer.setCity(proposerDTO.getCity());

        if (proposerDTO.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }
        proposer.setDateOfBirth(proposerDTO.getDateOfBirth());

        if (proposerDTO.getEmail() == null) {
            if (!proposerDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") || proposalRepo.existsByEmail(proposerDTO.getEmail())) {
                throw new IllegalArgumentException("Invalid email format.");
            }

        }
        proposer.setEmail(proposerDTO.getEmail());

        if (null == proposerDTO.getGender()) {
            throw new IllegalArgumentException("Gender cannot be null or empty.");
        }
        if (!proposerDTO.getGender().equalsIgnoreCase("male") && !proposerDTO.getGender().equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("Invalid gender. Only Male or Female are allowed.");
        }


        proposer.setGender(String.valueOf(proposerDTO.getGender()));


        if (proposerDTO.getMaritalstatus() == null) {
            throw new IllegalArgumentException("Marital status cannot be null or empty.");
        }
        if (!proposerDTO.getMaritalstatus().equalsIgnoreCase("single") && !proposerDTO.getMaritalstatus().equalsIgnoreCase("married") && !proposerDTO.getMaritalstatus().equalsIgnoreCase("divorce")) {
            throw new IllegalArgumentException("Enter valid marital status");
        }
        proposer.setMaritalstatus(proposerDTO.getMaritalstatus());


        if (proposerDTO.getName() == null || proposerDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        proposer.setName(proposerDTO.getName());
        boolean b = proposalRepo.existsByPanNumber(proposerDTO.getPanNumber());
        if (b) {
            throw new IllegalArgumentException("Pan card already present");
        }
        if (proposerDTO.getPanNumber() == null || proposerDTO.getPanNumber().isEmpty()) {
            if (!proposerDTO.getPanNumber().matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
                throw new IllegalArgumentException("PAN number must be exactly 10 characters.");
            }
        }

        proposer.setPanNumber(proposerDTO.getPanNumber());

        if (proposerDTO.getPhoneNumber() == null) {
            if (proposerDTO.getPhoneNumber().length() != 10 || proposalRepo.existsByPhonenumber(proposerDTO.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
            }
        }
        proposer.setPhonenumber(proposerDTO.getPhoneNumber());

        if (proposerDTO.getPincode() == null || proposerDTO.getPincode().length() != 6) {
            throw new IllegalArgumentException("Pincode must of 6 digit");
        }
        proposer.setPincode(proposerDTO.getPincode());

        if (proposerDTO.getState() == null || proposerDTO.getState().isEmpty()) {
            throw new IllegalArgumentException("State cannot be null or empty.");
        }
        proposer.setState(proposerDTO.getState());

        proposer.setStatus('Y');

        return proposalRepo.save(proposer);

    }

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Proposer> getDetails(ProposerPage proposerPage)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Proposer> criteriaQuery = criteriaBuilder.createQuery(Proposer.class);
        Root<Proposer> root = criteriaQuery.from(Proposer.class);

        if(proposerPage.getPage() >= 0 && proposerPage.getSize() >= 0)
        {
            if(proposerPage.getSortBy() == null  || proposerPage.getSortOrder().isEmpty())
            {
                proposerPage.setSortBy("id");
                proposerPage.setSortOrder("DESC");
            }
        }else{
            throw new IllegalArgumentException("Galat hai");
        }

        if(proposerPage.getSortBy() != null && proposerPage.getSortOrder() != null)
        {
            String sortBy = proposerPage.getSortBy();
            if("ASC".equalsIgnoreCase(proposerPage.getSortOrder()))
            {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
            }
            else
            {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
            }
        }

        if(proposerPage.getPage() <= 0 && proposerPage.getSize() <= 0)
        {
            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        else
        {
            Integer size = proposerPage.getSize();
            Integer page = proposerPage.getPage();

            TypedQuery<Proposer> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((page -1) * size);
            typedQuery.setMaxResults(size);

            return typedQuery.getResultList();
        }
    }

    @Override
    public List<Proposer> getfiltering(ProposerPage proposerPage)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Proposer> criteriaQuery = criteriaBuilder.createQuery(Proposer.class);
        Root<Proposer> root = criteriaQuery.from(Proposer.class);

        SearchFilter[] searchFilters = proposerPage.getSearchFilters();

        if (searchFilters != null) {
            for (SearchFilter filter : searchFilters) {
                if (filter.getName() != null && !filter.getName().isEmpty())
                {
                   // criteriaQuery.where(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
                    criteriaQuery.where(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
                }
                if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                    criteriaQuery.where(criteriaBuilder.like(root.get("email"), "%" + filter.getEmail() + "%"));
                }
                if (filter.getCity() != null && !filter.getCity().isEmpty()) {
                    criteriaQuery.where(criteriaBuilder.like(root.get("city"), "%" + filter.getCity() + "%"));
                }
                if (filter.getStatus() != null) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
                }
            }
        }

        if(proposerPage.getPage() >= 0 && proposerPage.getSize() >= 0)
        {
            if(proposerPage.getSortBy() == null  || proposerPage.getSortOrder().isEmpty())
            {
                proposerPage.setSortBy("id");
                proposerPage.setSortOrder("DESC");
            }
        }else{
            throw new IllegalArgumentException("Galat hai");
        }

        if(proposerPage.getSortBy() != null && proposerPage.getSortOrder() != null)
        {
            String sortBy = proposerPage.getSortBy();
            if("ASC".equalsIgnoreCase(proposerPage.getSortOrder()))
            {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
            }
            else
            {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
            }
        }

        if(proposerPage.getPage() <= 0 && proposerPage.getSize() <= 0)
        {
            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        else
        {
            Integer size = proposerPage.getSize();
            Integer page = proposerPage.getPage();

            TypedQuery<Proposer> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((page -1) * size);
            typedQuery.setMaxResults(size);

            return typedQuery.getResultList();
        }
    }


    @Override
    public Proposer getProposerById(Long id) {

        return proposalRepo.getById(id);

    }

    @Override
    public Proposer saveProposer(Proposer proposer) {
        return null;
    }


    @Override
    public Proposer updatedto(Long id, ProposerDTO proposerDTO) {

        Proposer proposer = new Proposer();

        if (proposerDTO.getAadharnumber() == null || proposerDTO.getAadharnumber().length() != 12) {
            throw new IllegalArgumentException("Aadhaar number must be exactly 12 digits.");
        }
        proposer.setAadharnumber(proposerDTO.getAadharnumber());

        if (proposerDTO.getAddress() == null || proposerDTO.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        proposer.setAddress(proposerDTO.getAddress());

        if (proposerDTO.getAnnualincome() == null || proposerDTO.getAnnualincome().isEmpty()) {
            throw new IllegalArgumentException("Annual income must be a positive value.");
        }
        proposer.setAnnualincome(proposerDTO.getAnnualincome());

        if (proposerDTO.getCity() == null || proposerDTO.getCity().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty.");
        }
        proposer.setCity(proposerDTO.getCity());

        if (proposerDTO.getDateOfBirth() == null)
        {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }
        proposer.setDateOfBirth(proposerDTO.getDateOfBirth());

        if (proposerDTO.getEmail() == null)
        {
            if (!proposerDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            {
                throw new IllegalArgumentException("Invalid email format.");
            }

        }
        proposer.setEmail(proposerDTO.getEmail());

        if (null == proposerDTO.getGender()) {
            throw new IllegalArgumentException("Gender cannot be null or empty.");
        }
        if (!proposerDTO.getGender().equalsIgnoreCase("male") && !proposerDTO.getGender().equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("Invalid gender. Only Male or Female are allowed.");
        }


        proposer.setGender(String.valueOf(proposerDTO.getGender()));


        if (proposerDTO.getMaritalstatus() == null) {
            throw new IllegalArgumentException("Marital status cannot be null or empty.");
        }
        if (!proposerDTO.getMaritalstatus().equalsIgnoreCase("single") && !proposerDTO.getMaritalstatus().equalsIgnoreCase("married") && !proposerDTO.getMaritalstatus().equalsIgnoreCase("divorce")) {
            throw new IllegalArgumentException("Enter valid marital status");
        }
        proposer.setMaritalstatus(proposerDTO.getMaritalstatus());


        if (proposerDTO.getName() == null || proposerDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        proposer.setName(proposerDTO.getName());

        if (proposerDTO.getPanNumber() == null || proposerDTO.getPanNumber().isEmpty()) {
            if (!proposerDTO.getPanNumber().matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
                throw new IllegalArgumentException("PAN number must be exactly 10 characters.");
            }
        }
        proposer.setPanNumber(proposerDTO.getPanNumber());

        if (proposerDTO.getPhoneNumber() == null) {
            if (proposerDTO.getPhoneNumber().length() != 10) {
                throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
            }
        }
        proposer.setPhonenumber(proposerDTO.getPhoneNumber());

        if (proposerDTO.getPincode() == null || proposerDTO.getPincode().length() != 6) {
            throw new IllegalArgumentException("Pincode must of 6 digit");
        }
        proposer.setPincode(proposerDTO.getPincode());

        if (proposerDTO.getState() == null || proposerDTO.getState().isEmpty()) {
            throw new IllegalArgumentException("State cannot be null or empty.");
        }
        proposer.setState(proposerDTO.getState());

        return proposalRepo.save(proposer);
    }

}








/*public List<AbstractReadWriteAccess.Item> getItems(int page, int size)
{
    int offset = page * size;
    return proposalRepo.findByIdd(size, offset);
}

public long getTotalItems()
{
    return proposalRepo.countTotalRecords();
}*/
