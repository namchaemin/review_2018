package bitcamp.java87.project01.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import bitcamp.java87.project01.dao.WardrobeDao;
import bitcamp.java87.project01.domain.Wardrobe;
import bitcamp.java87.project01.service.UserService;
import bitcamp.java87.project01.service.WardrobeService;

@Service("wardrobeServiceImpl")
public class WardrobeServiceImpl implements WardrobeService {

  /// Field
  @Autowired
  @Qualifier("wardrobeDaoImpl")
  private WardrobeDao wardrobeDao;

  /// Constructor
  public WardrobeServiceImpl() {
    System.out.println(this.getClass());
  }


  /// Method
  public void addWardrobe(Wardrobe wardrobe) throws Exception {
    System.out.println("[wardrobeService] ::: addWardrobe");
    wardrobeDao.addWardrobe(wardrobe);
  }

  public Wardrobe getWardrobe(Wardrobe wardrobe) throws Exception {
    System.out.println("[wardrobeService] ::: getWardrobe(wardrobe)");
    return wardrobeDao.getWardrobe(wardrobe);
  }

  public Wardrobe getWardrobe(int cls_no) throws Exception {
    System.out.println("[wardrobeService] ::: getWardrobe(cls_no)");
    return wardrobeDao.getWardrobe(cls_no);
  }

  public Map<String, Object> getWardrobeList(int user_no) throws Exception {
    System.out.println("[wardrobeService] ::: getWardrobeList");
    return wardrobeDao.getWardrobeList(user_no);
  }

  public void updateWardrobe(Wardrobe wardrobe) throws Exception {
    System.out.println("[wardrobeService] ::: updateWardrobe");
    wardrobeDao.updateWardrobe(wardrobe);
  }

  public void deleteWardrobe(int cls_no) throws Exception {
    System.out.println("[wardrobeService] ::: deleteWardrobe");
    wardrobeDao.deleteWardrobe(cls_no);
  }



  public void addFollow(Wardrobe wardrobe) throws Exception {
    System.out.println("[wardrobeService] ::: addFollow");
    wardrobeDao.addFollow(wardrobe);
  }

  public void deleteFollow(Wardrobe wardrobe) throws Exception {
    System.out.println("[wardrobeService] ::: deleteFollow");
    wardrobeDao.deleteFollow(wardrobe);
  }

  public int getFollow(Wardrobe wardrobe) throws Exception {
    System.out.println("[wardrobeService] ::: getFollow(wardrobe)");
    return wardrobeDao.getFollow(wardrobe);
  }

  public Map<String, Object> getFollowerList(int following) throws Exception {
    System.out.println("[wardrobeService] ::: getFollowerList ::: start");
    List<Wardrobe> followerList = wardrobeDao.getFollowerList(following);

    System.out.println("[wardrobeService]  ::: getFollowerList ::: List :::" + followerList);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("followerList", followerList);

    System.out.println("[wardrobeService] ::: getFollwerList ::: end");
    
    return map;
  }

  public Map<String, Object> getFollowingList(int follower) throws Exception {
    System.out.println("[wardrobeService] ::: getFollowingList ::: start");
    List<Wardrobe> followingList = wardrobeDao.getFollowingList(follower);
    
    System.out.println("[wardrobeService]  ::: getFollowingList ::: List :::" + followingList);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("followingList", followingList);

    System.out.println("[wardrobeService] ::: getFollwerList ::: end");
    
    return map;
  }

}
