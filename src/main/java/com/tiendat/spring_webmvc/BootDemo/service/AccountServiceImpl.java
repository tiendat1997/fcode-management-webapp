package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Role;
import com.tiendat.spring_webmvc.BootDemo.model.UserAccount;
import com.tiendat.spring_webmvc.BootDemo.respository.AccountRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.RoleRespository;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRespository;

    @Autowired
    private RoleRespository roleRespository;

    /* (non-Javadoc)
	 * @see com.tiendat.spring_webmvc.BootDemo.service.AccountService#findUserRole(int)
     */
    @Override
    public Role findUserRole(int roleId) {
        return this.roleRespository.findByRoleId(roleId);
    }

    /* (non-Javadoc)
	 * @see com.tiendat.spring_webmvc.BootDemo.service.AccountService#findByUsernameAndPassword(java.lang.String, java.lang.String)
     */
    @Override
    public Account findByUsernameAndPassword(String username, String password) {
        // TODO Auto-generated method stub
        return this.accountRespository.findByUsernameAndPassword(username, password);
    }

    // Get All Account that don't be expired
    @Override
    public List<Account> findAllAccountByExpired(boolean expired) {
        return this.accountRespository.findByExpired(expired);
    }

    // 
    @Override
    public Page<Account> findAccountPaginated(int option, int page, int size) {
        if (option == 3) {
            return this.accountRespository.findAll(new PageRequest(page, size));
        } else if (option == 1) {
            return this.accountRespository.findAll(false, new PageRequest(page, size));
        }
        return this.accountRespository.findAll(true, new PageRequest(page, size));
    }

    @Override
    public List<Account> findAccountByGrade(int grade) {

        return this.accountRespository.findByGrade(grade);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRespository.findByUsername(username);
    }

    @Override
    public Account addAccount(Account account) {
        return accountRespository.save(account);
    }

    @Override
    public Account deleteAccount(String username) {
        Account account = accountRespository.findByUsername(username);
        account.setExpired(true);
        return accountRespository.save(account);
    }

    @Override
    public Account restoreAccount(String username) {
        Account account = accountRespository.findByUsername(username);
        account.setExpired(false);
        return accountRespository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRespository.save(account);
    }

    @Override
    public int updateMemberAdmin(String username, String fullname, Integer grade, String major, String email, String phone) {
        return accountRespository.updateMemberAdmin(username, fullname, grade, major, email, phone);
    }

    @Transactional()
    public Page<Account> filterAccountPaginated(
            String username,
            String fullname,
            String email,
            String phone,
            Integer grade,
            int option,
            int page, int size) {
        if (grade != null) {
            if (option == 3) {
                return this.accountRespository.filterAccount(username, fullname, email, phone, grade, new PageRequest(page, size));
            } else if (option == 1) {
                return this.accountRespository.filterAccount(username, fullname, email, phone, grade, false, new PageRequest(page, size));
            }
            return this.accountRespository.filterAccount(username, fullname, email, phone, grade, true, new PageRequest(page, size));

        } else {
            if (option == 3) {
                return this.accountRespository.filterAccount(username, fullname, email, phone, new PageRequest(page, size));
            } else if (option == 1) {
                return this.accountRespository.filterAccount(username, fullname, email, phone, false, new PageRequest(page, size));
            }
            return this.accountRespository.filterAccount(username, fullname, email, phone, true, new PageRequest(page, size));
        }

    }

    @Override
    public boolean accountExisted(String username) {
        Account account = this.accountRespository.findByUsername(username);
        if (account == null) {
            return false;
        }
        return true;
    }

    @Override
    public Account addNewAccount(Account account) {
        return this.accountRespository.save(account);
    }

    @Override
    public List<Account> findByRoleId(int roleId) {
        return this.accountRespository.findByRoleId(roleId);
    }

    @Override
    public List<Account> findAdminAndModerator(int roleAdmin, int roleModerator) {
        return this.accountRespository.findModeratorAndAdmin(roleAdmin, roleModerator);
    }

    @Override
    public List<Account> findTop10ByFullname(String fullname) {
        return this.accountRespository.findTop10ByFullnameContaining(fullname);
    }

    @Override
    public int countMemberByRoleId(int roleId) {
        return this.accountRespository.countByRoleId(roleId);
    }

	@Override
	public List<UserAccount> findTop10ByFullnameForUser(String fullname) {
		List<Account> list = accountRespository.findTop10ByFullnameContaining(fullname);
		List<UserAccount> listAccount = null;
		for(Account account: list) {
			if (listAccount == null) {
				listAccount = new ArrayList<>();
			}
			listAccount.add(new UserAccount(account.getUsername(), account.getFullname(), account.getStudentCode()));
		}
		return listAccount;
	}

}
