package me.madrapeau.sbieaspiprestfulapi;

        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMethod;
        import javax.persistence.EntityManager;
        import javax.persistence.*;

@RestController
public class UserHasRead {
    @PersistenceContext
    EntityManager entityManager;
    @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/has_read", method = RequestMethod.GET)
    @ResponseBody
    public Boolean getHasRead(
            @PathVariable("user_id") long user_id, @PathVariable("account_id") long account_id) {

        User user = entityManager.find(User.class, user_id);

        for (Right r: user.getRights()) {
            if (r.getAccountId()==account_id && r.getPermissionCode() == "Read") {
                return true;
            }
        }
        return false;
    }
}



