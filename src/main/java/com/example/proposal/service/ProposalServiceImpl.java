package com.example.proposal.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.proposal.model.Gender;
import com.example.proposal.repository.newProposerRepo;
//import com.example.proposal.model.Gender;
import com.example.proposal.model.GenderType;
import jakarta.persistence.criteria.*;

import com.example.proposal.pagenation.ProposerPage;
import com.example.proposal.pagenation.SearchFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proposal.dto.ProposerDTO;
import com.example.proposal.repository.ProposalRepo;
import com.example.proposal.model.Proposer;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepo proposalRepo;

    @Autowired
    private newProposerRepo newProposerRepo;

    int totalRecord = 0;

    @Override

    public Proposer register(Proposer proposer) {
        proposer.setStatus('Y');
        Proposer save = proposalRepo.save(proposer);
        return save;
    }


    @Override
    public Proposer delete(Long id) throws Exception {
        Optional<Proposer> deleteProposer = proposalRepo.findById(id);
        Proposer updateDeletedStatus = deleteProposer.get();
        if (updateDeletedStatus.getStatus() == 'N') {
            throw new Exception("Proposer is deleted");

        }

        updateDeletedStatus.setStatus('N');
        return proposalRepo.save(updateDeletedStatus);


    }

    @Override
    public List<Proposer> getProposer() {
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
        oldproposer.setphoneNumber(proposer.getphoneNumber());
        oldproposer.setPincode(proposer.getPincode());
        oldproposer.setState(proposer.getState());


        Proposer updatedProposer = proposalRepo.save(oldproposer);
        return updatedProposer;

    }

    @Override
    public Proposer saveProposerDto(ProposerDTO proposerDTO) {
        Proposer proposer = new Proposer();

        String genderType = proposerDTO.getGender();
        if (genderType != null && !genderType.isEmpty()) {
            Optional<GenderType> genderTable = newProposerRepo.findByGenderType(genderType);
            if (genderTable.isPresent()) {
                proposer.setGenderId(genderTable.get().getGenderID());
            } else {
                throw new IllegalArgumentException("Invalid gender type");
            }
        } else {
            throw new IllegalArgumentException("Gender not be null or empty");
        }

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
            if (proposerDTO.getPhoneNumber().length() != 10 || proposalRepo.existsByPhoneNumber(proposerDTO.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
            }
        }
        proposer.setphoneNumber(proposerDTO.getPhoneNumber());

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
    public List<Proposer> getDetails(ProposerPage proposerPage) {

        List<SearchFilter> searchFilters = proposerPage.getSearchFilters();

        String city = "";
        String name = "";
        String status = "";

        for (SearchFilter result : searchFilters) {
            name = result.getName();
            city = result.getCity();
            status = result.getStatus();

        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Proposer> criteriaQuery = criteriaBuilder.createQuery(Proposer.class);
        Root<Proposer> root = criteriaQuery.from(Proposer.class);
        criteriaQuery.select(root);

        List<Predicate> predicate = new ArrayList<>();


        if (status != null && "N".equalsIgnoreCase(status)) {
            predicate.add(criteriaBuilder.equal(root.get("status"), 'N'));
        } else {
            predicate.add(criteriaBuilder.equal(root.get("status"), 'Y'));
        }


        String sortBy1 = proposerPage.getSortBy();
        String sortOrder = proposerPage.getSortOrder();

        String sort = sortBy1 != null ? sortBy1 : "creatAt";
        String sortOr = sortOrder != null ? sortOrder : "desc";


        if (!city.isEmpty()) {
            predicate.add(criteriaBuilder.equal(root.get("city"), city));
        }
        if (!name.isEmpty()) {
            predicate.add(criteriaBuilder.equal(root.get("name"), name));
        }
        criteriaQuery.where(predicate.toArray(new Predicate[0]));

        if (!sortBy1.isEmpty() && !sortOrder.isEmpty()) {

            if ("ASC".equalsIgnoreCase(sortBy1)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy1)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy1)));
            }
        }

        Integer size = proposerPage.getSize();
        Integer page = proposerPage.getPage();

        TypedQuery<Proposer> typedQuery = entityManager.createQuery(criteriaQuery);


        List<Proposer> resultList = typedQuery.getResultList();
        int sized = resultList.size();
        totalRecord = sized;

        System.err.println(sized);
        if (page > 0 && size > 0) {

            typedQuery.setFirstResult((page - 1) * size);
            typedQuery.setMaxResults(size);
        }
        return typedQuery.getResultList();
    }

    @Override
    public Integer getTotalRecord() {
        return totalRecord;
    }

    @Override
    public void generateExcel(HttpServletResponse httpServletResponse) throws Exception {
        List<Proposer> proposers = proposalRepo.findAll();


        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet("Proposer_Details");

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("name");
        row.createCell(2).setCellValue("gender");
        row.createCell(3).setCellValue("status");

        int dataRowIndex = 1;

        for (Proposer proposer : proposers) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex++);
            dataRow.createCell(0).setCellValue(proposer.getId());
            dataRow.createCell(1).setCellValue(proposer.getName());
            dataRow.createCell(2).setCellValue(proposer.getGender());
            dataRow.createCell(3).setCellValue(String.valueOf(proposer.getStatus()));
//            dataRowIndex++;
        }

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        xssfWorkbook.write(outputStream);
        xssfWorkbook.close();
        outputStream.close();
    }

    @Override
    public String sampleExcel(String filePath) throws Exception {
        List<Proposer> proposers = proposalRepo.findAll();

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet();

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("name");
        row.createCell(2).setCellValue("gender");
        row.createCell(3).setCellValue("status");

        int rowCount = 1;
//        for (Proposer proposer : proposers) {
//            XSSFRow dataRow = sheet.createRow(rowCount++);
//            dataRow.createCell(0).setCellValue(proposer.getId());
//            dataRow.createCell(1).setCellValue(proposer.getName());
//            dataRow.createCell(2).setCellValue(proposer.getGender());
//            dataRow.createCell(3).setCellValue(proposer.getStatus());
//        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            xssfWorkbook.write(fileOut);
        }

        xssfWorkbook.close();
        return filePath;
    }


    public List<Proposer> importPersonalDetailsFromExcel(MultipartFile file) throws IOException {
        List<Proposer> savedExcelList = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;

                Proposer dto = new Proposer();


                Cell dobCell = row.getCell(4); // Use correct DOB index!
                if (dobCell != null && dobCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dobCell)) {
                    LocalDate dob = dobCell.getLocalDateTimeCellValue().toLocalDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    dto.setDateOfBirth(dob.format(formatter));
//                    dto.setDateOfBirth(dobCell.getLocalDateTimeCellValue().toLocalDate());

                } else {
                    dto.setDateOfBirth(getCellString(row.getCell(4)));
                }
//                Proposer proposer = new Proposer();

                dto.setAadharnumber(getCellString(row.getCell(0)));
                dto.setName(getCellString(row.getCell(1)));
                dto.setGender(getCellString(row.getCell(2)));
                dto.setState(getCellString(row.getCell(3)));
//                dto.setDateOfBirth(getCellString(row.getCell(4)));
                dto.setAnnualincome(getCellString(row.getCell(5)));
                dto.setPanNumber(getCellString(row.getCell(6)));
                dto.setMaritalstatus(getCellString(row.getCell(7)));
                //  dto.set(getCellString(row.getCell(6)));
                //  dto.setProfession(getCellString(row.getCell(7)));
                dto.setEmail(getCellString(row.getCell(8)));
                dto.setphoneNumber(getCellString(row.getCell(9)));
                // dto.setAlternateMobileNumber(getCellString(row.getCell(10)));
                dto.setAddress(getCellString(row.getCell(10)));
                //dto.setAddressLine2(getCellString(row.getCell(12)));
                // dto.setAddressLine3(getCellString(row.getCell(13)));
                dto.setPincode(getCellString(row.getCell(11)));
                dto.setCity(getCellString(row.getCell(12)));
//                dto.setState(getCellString(row.getCell(16)));

                proposalRepo.save(dto);
//                savedExcelList.add(saved);

            }

        }

        return savedExcelList;
    }

    private String getCellString(Cell cell) {
        if (cell == null) return "";

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC && !DateUtil.isCellDateFormatted(cell)) {
            return String.valueOf((long) cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else {
            return "";
        }
    }






    /*@Override
    public List<Proposer> getfiltering(ProposerPage proposerPage)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Proposer> criteriaQuery = criteriaBuilder.createQuery(Proposer.class);
        Root<Proposer> root = criteriaQuery.from(Proposer.class);

        List<Predicate> predicates = new ArrayList<>();

        List<SearchFilter> searchFilters = proposerPage.getSearchFilters();
        for (SearchFilter result : searchFilters) {
        }

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

//        criteriaQuery.where(predicates.toArray(new Predicate[0]));

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
    }*/


    @Override
    public Proposer getProposerById(Long id) {

        return proposalRepo.getById(id);

    }

    @Override
    public Proposer saveProposer(Proposer proposer) {
        return null;
    }


    @Override
    public Proposer updateDto(Long id, ProposerDTO proposerDTO) {

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

        if (proposerDTO.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }
        proposer.setDateOfBirth(proposerDTO.getDateOfBirth());

        if (proposerDTO.getEmail() == null) {
            if (!proposerDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
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
        proposer.setphoneNumber(proposerDTO.getPhoneNumber());

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
