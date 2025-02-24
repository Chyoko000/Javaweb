package service.impl;

import com.situ.web.dao.IBanjiDao;
import com.situ.web.dao.impl.BanjiDaoImpl;
import com.situ.web.pojo.Banji;
import com.situ.web.util.PageInfo;
import service.IBanjiService;

import java.util.List;

public class BanjiServiceImpl implements IBanjiService {
    private IBanjiDao banjiDao = new BanjiDaoImpl();

    @Override
    public List<Banji> selectAll() {
        return banjiDao.selectAll();
    }

    @Override
    public void deleteById(int id) {
        banjiDao.deleteById(id);
    }

    @Override
    public void add(Banji banji) {
        banjiDao.add(banji);
    }

    @Override
    public Banji selectById(int id) {
        return banjiDao.selectById(id);
    }

    @Override
    public void update(Banji banji) {
        banjiDao.update(banji);
    }


    //业务层
    @Override
    public PageInfo<Banji> selectByPage(int pageNo, int pageSize) {
        //第一条sql：查询当前页的数据
        int offset = (pageNo - 1) * pageSize;
        List<Banji> list = banjiDao.selectByPage(offset, pageSize);
        //第二条sql：查询总的数量
        int totalCount = banjiDao.selectTotalCount();
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        PageInfo<Banji> pageInfo = new PageInfo<>(list, totalPage, pageNo, pageSize);
        return pageInfo;
    }

    public static void main(String[] args) {
        int totalCount = 12;
        int pageSize = 5;
        System.out.println(totalCount / pageSize);//2
        System.out.println((double)totalCount / pageSize);//2.4
        System.out.println(Math.ceil((double)totalCount / pageSize));//3.0
        System.out.println(Math.floor((double)totalCount / pageSize));//2.0

        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        System.out.println(totalPage);//3
    }
}
