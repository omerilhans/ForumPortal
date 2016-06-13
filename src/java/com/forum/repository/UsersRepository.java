package com.forum.repository;

import com.forum.database.BaseRepository;
import com.forum.entity.Users;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UsersRepository extends BaseRepository<Users> {

    public UsersRepository() {
        super(Users.class);
    }

    public Users findByUserNickname(String userNickname) {
        try {
            String jpql = createSelectSQL()
                    + " where users.userNickname = :userNickname";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("userNickname", userNickname);
            Users user = (Users) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
        
    }
    
    public List<Users> listUsersByDegreeId(int degreeId)
    {
        String jpq = "select users from Users as users where users.degree.degreeId = :degreeId";
        Query query = entityManager.createQuery(jpq);
        query.setParameter("degreeId",degreeId);
        return query.getResultList();
    }
    

}
