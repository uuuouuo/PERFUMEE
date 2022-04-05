package com.ssafy.perfumee.repository.taste;

import com.ssafy.perfumee.model.entity.user.Taste;
import com.ssafy.perfumee.model.entity.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasteRepository extends JpaRepository<Taste,Integer> {

  public List<Taste> findAllByUser(User user);

}
