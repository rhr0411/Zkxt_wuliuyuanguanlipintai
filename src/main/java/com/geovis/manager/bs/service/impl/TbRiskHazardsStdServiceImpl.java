package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geovis.manager.bs.dto.TbRiskHazardsStdTree;
import com.geovis.manager.bs.entity.TbRiskHazardsStd;
import com.geovis.manager.bs.mapper.TbRiskHazardsStdMapper;
import com.geovis.manager.bs.service.ITbRiskHazardsStdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 * 风险隐患管理_排查标准 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbRiskHazardsStdServiceImpl extends ServiceImpl<TbRiskHazardsStdMapper, TbRiskHazardsStd> implements ITbRiskHazardsStdService {

//
//    @Resource
//    private TbRiskHazardsStdMapper tbRiskHazardsStdMapper;

    @Override
    public List<TbRiskHazardsStdTree> getTree() {
        List<TbRiskHazardsStd> toplist = getToplist();

        List<TbRiskHazardsStdTree> tbRiskHazardsStdTrees = new ArrayList<>();

        toplist.forEach(new Consumer<TbRiskHazardsStd>() {
            @Override
            public void accept(TbRiskHazardsStd tbRiskHazardsStd) {
                TbRiskHazardsStdTree tbRiskHazardsStdTree = BeanUtil.copyProperties(tbRiskHazardsStd, TbRiskHazardsStdTree.class);
                String id=tbRiskHazardsStdTree.getId();

                tbRiskHazardsStdTree.setTbRiskHazardsStdTrees(getTree(id));


                tbRiskHazardsStdTrees.add(tbRiskHazardsStdTree);
            }
        });

        return tbRiskHazardsStdTrees;
    }


    /**
     * 获取所有父节点
     * @return
     */
    public List<TbRiskHazardsStd> getToplist(){
        List<TbRiskHazardsStd> list = this.list();
        return list.stream().filter(tbRiskHazardsStd -> StrUtil.isBlank(tbRiskHazardsStd.getParentId())).collect(Collectors.toList());
    }

    /**
     * 遍历该节点的所有孩子节点，并返回
     * @param id
     * @return
     */
    public List<TbRiskHazardsStdTree> forEachChild(String id){
        List<TbRiskHazardsStdTree> listTree = new ArrayList<>();
        //所有孩子节点
        List<TbRiskHazardsStd> list = list(new LambdaQueryWrapper<TbRiskHazardsStd>().eq(StrUtil.isNotBlank(id), TbRiskHazardsStd::getParentId, id));
        list.forEach(new Consumer<TbRiskHazardsStd>() {
            @Override
            public void accept(TbRiskHazardsStd tbRiskHazardsStd) {
                TbRiskHazardsStdTree tbRiskHazardsStdTree = BeanUtil.copyProperties(tbRiskHazardsStd, TbRiskHazardsStdTree.class);
                listTree.add(tbRiskHazardsStdTree);
            }
        });

        return listTree;
    }

    /**
     * 根据id获取所有的树形子节点
     * @param id
     * @return
     */
    public List<TbRiskHazardsStdTree> getTree(String id){
        List<TbRiskHazardsStdTree> listTree =new ArrayList<>();

        List<TbRiskHazardsStdTree> tbRiskHazardsStdTrees = forEachChild(id);
        listTree.addAll(tbRiskHazardsStdTrees);
//        int size = listTree.size();
        //-------
        int i=0;
        while(i<tbRiskHazardsStdTrees.size()){
            TbRiskHazardsStdTree tbRiskHazardsStdTree = tbRiskHazardsStdTrees.get(i);
            List<TbRiskHazardsStdTree> temp = forEachChild(tbRiskHazardsStdTree.getId());
            if(!(temp.isEmpty())){
                //-------
                //--加入队列
                tbRiskHazardsStdTrees.addAll(temp);
                //
                tbRiskHazardsStdTree.setTbRiskHazardsStdTrees(temp);
//                while(size>0) {
//                    listTree.add(tbRiskHazardsStdTree);
//                    size--;
//                }
            }
            i++;
        }
        return listTree;

    }


}
