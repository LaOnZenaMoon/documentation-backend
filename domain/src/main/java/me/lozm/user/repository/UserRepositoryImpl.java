package me.lozm.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.lozm.common.vo.PageVo;
import me.lozm.common.vo.SearchVo;
import me.lozm.user.vo.UserInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.lozm.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<UserInfoVo> getUserList(PageVo pageVo, SearchVo searchVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                        UserInfoVo.class,
                        user.id.as("id"),
                        user.email.as("email"),
                        user.name.as("name"),
                        user.createdBy.as("createdBy"),
                        user.lastModifiedBy.as("lastModifiedBy"),
                        user.use.as("use"),
                        user.createdDateTime.as("createdDateTime"),
                        user.lastModifiedDateTime.as("lastModifiedDateTime")
                ))
                .from(user)
                .where()
                .orderBy(user.id.desc())
                .offset(pageVo.getPageRequest().getOffset())
                .limit(pageVo.getPageRequest().getPageSize())
                .fetch();
    }

    @Override
    public long getUserTotalCount(PageVo pageVo, SearchVo searchVo) {
        return jpaQueryFactory
                .selectFrom(user)
                .where()
                .orderBy(user.id.desc())
                .offset(pageVo.getPageRequest().getOffset())
                .limit(pageVo.getPageRequest().getPageSize())
                .fetchCount();
    }

}
