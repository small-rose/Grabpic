package com.xiaocai.web.demo.entity.website;

import java.util.ArrayList;
import java.util.List;

public class WebPictrueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebPictrueExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPicIdIsNull() {
            addCriterion("pic_id is null");
            return (Criteria) this;
        }

        public Criteria andPicIdIsNotNull() {
            addCriterion("pic_id is not null");
            return (Criteria) this;
        }

        public Criteria andPicIdEqualTo(String value) {
            addCriterion("pic_id =", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotEqualTo(String value) {
            addCriterion("pic_id <>", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdGreaterThan(String value) {
            addCriterion("pic_id >", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdGreaterThanOrEqualTo(String value) {
            addCriterion("pic_id >=", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdLessThan(String value) {
            addCriterion("pic_id <", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdLessThanOrEqualTo(String value) {
            addCriterion("pic_id <=", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdLike(String value) {
            addCriterion("pic_id like", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotLike(String value) {
            addCriterion("pic_id not like", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdIn(List<String> values) {
            addCriterion("pic_id in", values, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotIn(List<String> values) {
            addCriterion("pic_id not in", values, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdBetween(String value1, String value2) {
            addCriterion("pic_id between", value1, value2, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotBetween(String value1, String value2) {
            addCriterion("pic_id not between", value1, value2, "picId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNull() {
            addCriterion("link_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNotNull() {
            addCriterion("link_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkIdEqualTo(String value) {
            addCriterion("link_id =", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdWithLinkEqualTo(String value) {
            addCriterion("p.link_id =", value, "linkId");
            return (Criteria) this;
        }

        
        public Criteria andLinkIdNotEqualTo(String value) {
            addCriterion("link_id <>", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThan(String value) {
            addCriterion("link_id >", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThanOrEqualTo(String value) {
            addCriterion("link_id >=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThan(String value) {
            addCriterion("link_id <", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThanOrEqualTo(String value) {
            addCriterion("link_id <=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLike(String value) {
            addCriterion("link_id like", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotLike(String value) {
            addCriterion("link_id not like", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIn(List<String> values) {
            addCriterion("link_id in", values, "linkId");
            return (Criteria) this;
        }
        
        public Criteria andLinkIdWithLinkIn(List<String> values) {
            addCriterion("p.link_id in", values, "linkId");
            return (Criteria) this;
        }
        
        public Criteria andLinkIdNotIn(List<String> values) {
            addCriterion("link_id not in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdBetween(String value1, String value2) {
            addCriterion("link_id between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotBetween(String value1, String value2) {
            addCriterion("link_id not between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andPicAddrIsNull() {
            addCriterion("pic_addr is null");
            return (Criteria) this;
        }

        public Criteria andPicAddrIsNotNull() {
            addCriterion("pic_addr is not null");
            return (Criteria) this;
        }

        public Criteria andPicAddrEqualTo(String value) {
            addCriterion("pic_addr =", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrNotEqualTo(String value) {
            addCriterion("pic_addr <>", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrGreaterThan(String value) {
            addCriterion("pic_addr >", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrGreaterThanOrEqualTo(String value) {
            addCriterion("pic_addr >=", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrLessThan(String value) {
            addCriterion("pic_addr <", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrLessThanOrEqualTo(String value) {
            addCriterion("pic_addr <=", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrLike(String value) {
            addCriterion("pic_addr like", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrNotLike(String value) {
            addCriterion("pic_addr not like", value, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrIn(List<String> values) {
            addCriterion("pic_addr in", values, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrNotIn(List<String> values) {
            addCriterion("pic_addr not in", values, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrBetween(String value1, String value2) {
            addCriterion("pic_addr between", value1, value2, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicAddrNotBetween(String value1, String value2) {
            addCriterion("pic_addr not between", value1, value2, "picAddr");
            return (Criteria) this;
        }

        public Criteria andPicNameIsNull() {
            addCriterion("pic_name is null");
            return (Criteria) this;
        }

        public Criteria andPicNameIsNotNull() {
            addCriterion("pic_name is not null");
            return (Criteria) this;
        }

        public Criteria andPicNameEqualTo(String value) {
            addCriterion("pic_name =", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotEqualTo(String value) {
            addCriterion("pic_name <>", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameGreaterThan(String value) {
            addCriterion("pic_name >", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameGreaterThanOrEqualTo(String value) {
            addCriterion("pic_name >=", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameLessThan(String value) {
            addCriterion("pic_name <", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameLessThanOrEqualTo(String value) {
            addCriterion("pic_name <=", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameLike(String value) {
            addCriterion("pic_name like", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotLike(String value) {
            addCriterion("pic_name not like", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameIn(List<String> values) {
            addCriterion("pic_name in", values, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotIn(List<String> values) {
            addCriterion("pic_name not in", values, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameBetween(String value1, String value2) {
            addCriterion("pic_name between", value1, value2, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotBetween(String value1, String value2) {
            addCriterion("pic_name not between", value1, value2, "picName");
            return (Criteria) this;
        }

        public Criteria andPicOldnameIsNull() {
            addCriterion("pic_oldname is null");
            return (Criteria) this;
        }

        public Criteria andPicOldnameIsNotNull() {
            addCriterion("pic_oldname is not null");
            return (Criteria) this;
        }

        public Criteria andPicOldnameEqualTo(String value) {
            addCriterion("pic_oldname =", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameNotEqualTo(String value) {
            addCriterion("pic_oldname <>", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameGreaterThan(String value) {
            addCriterion("pic_oldname >", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameGreaterThanOrEqualTo(String value) {
            addCriterion("pic_oldname >=", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameLessThan(String value) {
            addCriterion("pic_oldname <", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameLessThanOrEqualTo(String value) {
            addCriterion("pic_oldname <=", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameLike(String value) {
            addCriterion("pic_oldname like", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameNotLike(String value) {
            addCriterion("pic_oldname not like", value, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameIn(List<String> values) {
            addCriterion("pic_oldname in", values, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameNotIn(List<String> values) {
            addCriterion("pic_oldname not in", values, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameBetween(String value1, String value2) {
            addCriterion("pic_oldname between", value1, value2, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameNotBetween(String value1, String value2) {
            addCriterion("pic_oldname not between", value1, value2, "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicDescIsNull() {
            addCriterion("pic_desc is null");
            return (Criteria) this;
        }

        public Criteria andPicDescIsNotNull() {
            addCriterion("pic_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPicDescEqualTo(String value) {
            addCriterion("pic_desc =", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotEqualTo(String value) {
            addCriterion("pic_desc <>", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescGreaterThan(String value) {
            addCriterion("pic_desc >", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescGreaterThanOrEqualTo(String value) {
            addCriterion("pic_desc >=", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescLessThan(String value) {
            addCriterion("pic_desc <", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescLessThanOrEqualTo(String value) {
            addCriterion("pic_desc <=", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescLike(String value) {
            addCriterion("pic_desc like", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotLike(String value) {
            addCriterion("pic_desc not like", value, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescIn(List<String> values) {
            addCriterion("pic_desc in", values, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotIn(List<String> values) {
            addCriterion("pic_desc not in", values, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescBetween(String value1, String value2) {
            addCriterion("pic_desc between", value1, value2, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicDescNotBetween(String value1, String value2) {
            addCriterion("pic_desc not between", value1, value2, "picDesc");
            return (Criteria) this;
        }

        public Criteria andPicSuffixIsNull() {
            addCriterion("pic_suffix is null");
            return (Criteria) this;
        }

        public Criteria andPicSuffixIsNotNull() {
            addCriterion("pic_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andPicSuffixEqualTo(String value) {
            addCriterion("pic_suffix =", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotEqualTo(String value) {
            addCriterion("pic_suffix <>", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixGreaterThan(String value) {
            addCriterion("pic_suffix >", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("pic_suffix >=", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLessThan(String value) {
            addCriterion("pic_suffix <", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLessThanOrEqualTo(String value) {
            addCriterion("pic_suffix <=", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLike(String value) {
            addCriterion("pic_suffix like", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotLike(String value) {
            addCriterion("pic_suffix not like", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixIn(List<String> values) {
            addCriterion("pic_suffix in", values, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotIn(List<String> values) {
            addCriterion("pic_suffix not in", values, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixBetween(String value1, String value2) {
            addCriterion("pic_suffix between", value1, value2, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotBetween(String value1, String value2) {
            addCriterion("pic_suffix not between", value1, value2, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicTypeIsNull() {
            addCriterion("pic_type is null");
            return (Criteria) this;
        }

        public Criteria andPicTypeIsNotNull() {
            addCriterion("pic_type is not null");
            return (Criteria) this;
        }

        public Criteria andPicTypeEqualTo(String value) {
            addCriterion("pic_type =", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeNotEqualTo(String value) {
            addCriterion("pic_type <>", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeGreaterThan(String value) {
            addCriterion("pic_type >", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pic_type >=", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeLessThan(String value) {
            addCriterion("pic_type <", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeLessThanOrEqualTo(String value) {
            addCriterion("pic_type <=", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeLike(String value) {
            addCriterion("pic_type like", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeNotLike(String value) {
            addCriterion("pic_type not like", value, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeIn(List<String> values) {
            addCriterion("pic_type in", values, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeNotIn(List<String> values) {
            addCriterion("pic_type not in", values, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeBetween(String value1, String value2) {
            addCriterion("pic_type between", value1, value2, "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeNotBetween(String value1, String value2) {
            addCriterion("pic_type not between", value1, value2, "picType");
            return (Criteria) this;
        }

        public Criteria andPicSizeIsNull() {
            addCriterion("pic_size is null");
            return (Criteria) this;
        }

        public Criteria andPicSizeIsNotNull() {
            addCriterion("pic_size is not null");
            return (Criteria) this;
        }

        public Criteria andPicSizeEqualTo(Long value) {
            addCriterion("pic_size =", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeNotEqualTo(Long value) {
            addCriterion("pic_size <>", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeGreaterThan(Long value) {
            addCriterion("pic_size >", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("pic_size >=", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeLessThan(Long value) {
            addCriterion("pic_size <", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeLessThanOrEqualTo(Long value) {
            addCriterion("pic_size <=", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeIn(List<Long> values) {
            addCriterion("pic_size in", values, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeNotIn(List<Long> values) {
            addCriterion("pic_size not in", values, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeBetween(Long value1, Long value2) {
            addCriterion("pic_size between", value1, value2, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeNotBetween(Long value1, Long value2) {
            addCriterion("pic_size not between", value1, value2, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNull() {
            addCriterion("pic_path is null");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNotNull() {
            addCriterion("pic_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicPathEqualTo(String value) {
            addCriterion("pic_path =", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotEqualTo(String value) {
            addCriterion("pic_path <>", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThan(String value) {
            addCriterion("pic_path >", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThanOrEqualTo(String value) {
            addCriterion("pic_path >=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThan(String value) {
            addCriterion("pic_path <", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThanOrEqualTo(String value) {
            addCriterion("pic_path <=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLike(String value) {
            addCriterion("pic_path like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotLike(String value) {
            addCriterion("pic_path not like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathIn(List<String> values) {
            addCriterion("pic_path in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotIn(List<String> values) {
            addCriterion("pic_path not in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathBetween(String value1, String value2) {
            addCriterion("pic_path between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotBetween(String value1, String value2) {
            addCriterion("pic_path not between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicDelIsNull() {
            addCriterion("pic_del is null");
            return (Criteria) this;
        }

        public Criteria andPicDelIsNotNull() {
            addCriterion("pic_del is not null");
            return (Criteria) this;
        }

        public Criteria andPicDelEqualTo(String value) {
            addCriterion("pic_del =", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelNotEqualTo(String value) {
            addCriterion("pic_del <>", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelGreaterThan(String value) {
            addCriterion("pic_del >", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelGreaterThanOrEqualTo(String value) {
            addCriterion("pic_del >=", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelLessThan(String value) {
            addCriterion("pic_del <", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelLessThanOrEqualTo(String value) {
            addCriterion("pic_del <=", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelLike(String value) {
            addCriterion("pic_del like", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelNotLike(String value) {
            addCriterion("pic_del not like", value, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelIn(List<String> values) {
            addCriterion("pic_del in", values, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelNotIn(List<String> values) {
            addCriterion("pic_del not in", values, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelBetween(String value1, String value2) {
            addCriterion("pic_del between", value1, value2, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicDelNotBetween(String value1, String value2) {
            addCriterion("pic_del not between", value1, value2, "picDel");
            return (Criteria) this;
        }

        public Criteria andPicMarkIsNull() {
            addCriterion("pic_mark is null");
            return (Criteria) this;
        }

        public Criteria andPicMarkIsNotNull() {
            addCriterion("pic_mark is not null");
            return (Criteria) this;
        }

        public Criteria andPicMarkEqualTo(String value) {
            addCriterion("pic_mark =", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkNotEqualTo(String value) {
            addCriterion("pic_mark <>", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkGreaterThan(String value) {
            addCriterion("pic_mark >", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkGreaterThanOrEqualTo(String value) {
            addCriterion("pic_mark >=", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkLessThan(String value) {
            addCriterion("pic_mark <", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkLessThanOrEqualTo(String value) {
            addCriterion("pic_mark <=", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkLike(String value) {
            addCriterion("pic_mark like", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkNotLike(String value) {
            addCriterion("pic_mark not like", value, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkIn(List<String> values) {
            addCriterion("pic_mark in", values, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkNotIn(List<String> values) {
            addCriterion("pic_mark not in", values, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkBetween(String value1, String value2) {
            addCriterion("pic_mark between", value1, value2, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicMarkNotBetween(String value1, String value2) {
            addCriterion("pic_mark not between", value1, value2, "picMark");
            return (Criteria) this;
        }

        public Criteria andPicNoIsNull() {
            addCriterion("pic_no is null");
            return (Criteria) this;
        }

        public Criteria andPicNoIsNotNull() {
            addCriterion("pic_no is not null");
            return (Criteria) this;
        }

        public Criteria andPicNoEqualTo(Integer value) {
            addCriterion("pic_no =", value, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoNotEqualTo(Integer value) {
            addCriterion("pic_no <>", value, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoGreaterThan(Integer value) {
            addCriterion("pic_no >", value, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("pic_no >=", value, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoLessThan(Integer value) {
            addCriterion("pic_no <", value, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoLessThanOrEqualTo(Integer value) {
            addCriterion("pic_no <=", value, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoIn(List<Integer> values) {
            addCriterion("pic_no in", values, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoNotIn(List<Integer> values) {
            addCriterion("pic_no not in", values, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoBetween(Integer value1, Integer value2) {
            addCriterion("pic_no between", value1, value2, "picNo");
            return (Criteria) this;
        }

        public Criteria andPicNoNotBetween(Integer value1, Integer value2) {
            addCriterion("pic_no not between", value1, value2, "picNo");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(String value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(String value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(String value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(String value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(String value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLike(String value) {
            addCriterion("add_time like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotLike(String value) {
            addCriterion("add_time not like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<String> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<String> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(String value1, String value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(String value1, String value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}