package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;

@RestController
@RequestMapping("/admin/api")
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    
    
    @GetMapping(value = "/member/get" , params = {"username"})
    public Account getAccount(@RequestParam("username") String username) {
    	return this.accountService.findByUsername(username);
    }
    

    // GetAccount not expired
    @GetMapping(value = "/member/current")
    public List<Account> getAllAccountNotExpired() {
        return this.accountService.findAllAccountByExpired(false);
    }

    // GetAccount expired
    @GetMapping(value = "/member/expired")
    public List<Account> getAllAccountExpired() {
        return this.accountService.findAllAccountByExpired(true);
    }

    //GetAccount by grade
    @GetMapping(value = "/member/grade/{grade}")
    public List<Account> getAccountByGrad(@PathVariable String grade) {
        return this.accountService.findAccountByGrade(Integer.parseInt(grade));
    }

    @GetMapping(value = "/account/get", params = {"optionExpired", "page", "size"})
    public Page<Account> findAccountPaginate(
            @RequestParam("optionExpired") int option,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        // option: 3 - both , 1 - current ,  2 - expired
        System.out.println("GetOption: " + option);
        Page<Account> resultPage = this.accountService.findAccountPaginated(option, page, size);

        // check if not found
        if (page > resultPage.getTotalPages()) {
            return null;
        }

        return resultPage;
    }

    @GetMapping(value = "/account/filter", params = {"username", "fullname", "email", "phone", "grade", "optionExpired", "page", "size"})
    public Page<Account> filterAccountPaginate(
            @RequestParam("username") String username,
            @RequestParam("fullname") String fullname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("grade") Integer grade,
            @RequestParam("optionExpired") int option,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<Account> resultPage = this.accountService.filterAccountPaginated(username, fullname, email, phone, grade, option, page, size);
        if (page > resultPage.getTotalPages()) {
            return null;
        }

        return resultPage;
    }

    //MEMBER REST CONTROLLER - SHOULD SEPERATE THIS  
    @GetMapping(value = "/member/delete", params = {"username"})
    public String deleteAccount(@RequestParam("username") String username) {
        String msg = "failure";
        Account result = this.accountService.deleteAccount(username);
        if (result != null) {
            msg = "success";
        }
        return msg;
    }

    @GetMapping(value = "/member/restore", params = {"username"})
    public String restoreAccount(@RequestParam("username") String username) {
        String msg = "failure";
        Account result = this.accountService.restoreAccount(username);
        if (result != null) {
            msg = "success";
        }
        return msg;
    }

    @GetMapping(value = "/member/update")
    public Account updateAccount(@ModelAttribute Account account) {
        return this.accountService.updateAccount(account);
    }

    // Add New Member
    @PostMapping(value = "/member/new",
            params = {"studentCode", "fullName", "email", "phone"})
    public String addNewAccount(
            @RequestParam("studentCode") String studentCode,
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone) {

        String msg = "null";

        String username = studentCode + ".fcode";
        String password = studentCode; // default password;
        String fullname = fullName;
        Integer roleId = 2;
        boolean expired = false;

        Account newMember = new Account(username, password, fullname, email, phone, roleId, studentCode, expired);
        System.out.println(newMember.toString());

        boolean existedAccount = this.accountService.accountExisted(username);
        if (existedAccount == false) {
            // Hasn't have this Account yet 
            // Save into DB
            Account result = this.accountService.addNewAccount(newMember);

            if (result != null) {
                msg = "success";
            } else {
                msg = "failure";
            }
        } else {
            msg = "duplicate";
        }

        return msg;

    }

    @GetMapping(value = "/member/get/admin")
    public List<Account> getListAdmin() {
        return this.accountService.findByRoleId(1);
    }

    @GetMapping(value = "/member/get/moderator")
    public List<Account> getListModerator() {
        return this.accountService.findByRoleId(3);
    }

    @GetMapping(value = "/member/get/adminAndModerator")
    public List<Account> getListAdminAndModerator() {
        return this.accountService.findAdminAndModerator(1, 3);
    }

    @GetMapping(value = "/member/get/10/", params = {"fullname"})
    public List<Account> getTop10ByFullname(@RequestParam("fullname") String fullname) {
        return this.accountService.findTop10ByFullname(fullname);
    }

    @GetMapping(value = "/member/update/admin", params = {"username", "roleId"})
    public String updateAdmin(@Param("username") String username, @Param("roleId") int roleId) {
        Account member = accountService.findByUsername(username);
        //set to admin
        if (roleId == 1) {

            //check member is admin or moderator already
            if (member.getRoleId() == 1 || member.getRoleId() == 3) {
                return "existed";
            }

            int numberOfAdmin = accountService.countMemberByRoleId(1);
            //max admin is 5
            if (numberOfAdmin >= 5) {
                return "enough";
            }
            member.setRoleId(roleId);
        }

        //set to user
        if (roleId == 2) {
            member.setRoleId(roleId);
        }

        //set to moderator
        if (roleId == 3) {

            //check member is admin or moderator already
            if (member.getRoleId() == 3 || member.getRoleId() == 1) {
                return "existed";
            }

            int numberOfModerator = accountService.countMemberByRoleId(3);
            //max moderator is 5
            if (numberOfModerator >= 5) {
                return "enough";
            }
            member.setRoleId(roleId);

        }
        accountService.addAccount(member);
        return "success";

    }

}
