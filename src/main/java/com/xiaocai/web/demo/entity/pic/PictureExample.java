package com.xiaocai.web.demo.entity.pic;

import java.util.ArrayList;
import java.util.List;

public class PictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PictureExample() {
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
            addCriterion("pic_id like", "%"+value+"%", "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotLike(String value) {
            addCriterion("pic_id not like", "%"+value+"%", "picId");
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

        public Criteria andUrlIdIsNull() {
            addCriterion("url_id is null");
            return (Criteria) this;
        }

        public Criteria andUrlIdIsNotNull() {
            addCriterion("url_id is not null");
            return (Criteria) this;
        }

        public Criteria andUrlIdEqualTo(String value) {
            addCriterion("url_id =", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotEqualTo(String value) {
            addCriterion("url_id <>", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdGreaterThan(String value) {
            addCriterion("url_id >", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdGreaterThanOrEqualTo(String value) {
            addCriterion("url_id >=", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdLessThan(String value) {
            addCriterion("url_id <", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdLessThanOrEqualTo(String value) {
            addCriterion("url_id <=", value, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdLike(String value) {
            addCriterion("url_id like", "%"+value+"%", "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotLike(String value) {
            addCriterion("url_id not like", "%"+value+"%", "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdIn(List<String> values) {
            addCriterion("url_id in", values, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotIn(List<String> values) {
            addCriterion("url_id not in", values, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdBetween(String value1, String value2) {
            addCriterion("url_id between", value1, value2, "urlId");
            return (Criteria) this;
        }

        public Criteria andUrlIdNotBetween(String value1, String value2) {
            addCriterion("url_id not between", value1, value2, "urlId");
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
            addCriterion("pic_name like", "%"+value+"%", "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotLike(String value) {
            addCriterion("pic_name not like", "%"+value+"%", "picName");
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
            addCriterion("pic_oldname like", "%"+value+"%", "picOldname");
            return (Criteria) this;
        }

        public Criteria andPicOldnameNotLike(String value) {
            addCriterion("pic_oldname not like", "%"+value+"%", "picOldname");
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
            addCriterion("pic_suffix like", "%"+value+"%", "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotLike(String value) {
            addCriterion("pic_suffix not like", "%"+value+"%", "picSuffix");
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
            addCriterion("pic_type like", "%"+value+"%", "picType");
            return (Criteria) this;
        }

        public Criteria andPicTypeNotLike(String value) {
            addCriterion("pic_type not like", "%"+value+"%", "picType");
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
            addCriterion("pic_path like", "%"+value+"%", "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotLike(String value) {
            addCriterion("pic_path not like", "%"+value+"%", "picPath");
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

        public Criteria andIsDelIsNull() {
            addCriterion("pic_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("pic_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(String value) {
            addCriterion("pic_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(String value) {
            addCriterion("pic_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(String value) {
            addCriterion("pic_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(String value) {
            addCriterion("pic_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(String value) {
            addCriterion("pic_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(String value) {
            addCriterion("pic_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLike(String value) {
            addCriterion("pic_del like", "%"+value+"%", "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotLike(String value) {
            addCriterion("pic_del not like", "%"+value+"%", "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<String> values) {
            addCriterion("pic_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<String> values) {
            addCriterion("pic_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(String value1, String value2) {
            addCriterion("pic_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(String value1, String value2) {
            addCriterion("pic_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsMarkIsNull() {
            addCriterion("pic_mark is null");
            return (Criteria) this;
        }

        public Criteria andIsMarkIsNotNull() {
            addCriterion("pic_mark is not null");
            return (Criteria) this;
        }

        public Criteria andIsMarkEqualTo(String value) {
            addCriterion("pic_mark =", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotEqualTo(String value) {
            addCriterion("pic_mark <>", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkGreaterThan(String value) {
            addCriterion("pic_mark >", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkGreaterThanOrEqualTo(String value) {
            addCriterion("pic_mark >=", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLessThan(String value) {
            addCriterion("pic_mark <", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLessThanOrEqualTo(String value) {
            addCriterion("pic_mark <=", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLike(String value) {
            addCriterion("pic_mark like", "%"+value+"%", "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotLike(String value) {
            addCriterion("pic_mark not like", "%"+value+"%", "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkIn(List<String> values) {
            addCriterion("pic_mark in", values, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotIn(List<String> values) {
            addCriterion("pic_mark not in", values, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkBetween(String value1, String value2) {
            addCriterion("pic_mark between", value1, value2, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotBetween(String value1, String value2) {
            addCriterion("pic_mark not between", value1, value2, "isMark");
            return (Criteria) this;
        }
        
        public Criteria andPicAddrLike(String value) {
            addCriterion("pic_addr like", "%"+value+"%", "isMark");
            return (Criteria) this;
        }

        public Criteria andPicAddrNotLike(String value) {
            addCriterion("pic_addr not like", "%"+value+"%", "isMark");
            return (Criteria) this;
        }
        
        public Criteria andPicAddrEqualTo(String value) {
            addCriterion("pic_addr =", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andPicAddrNotEqualTo(String value) {
            addCriterion("pic_addr <>", value, "isMark");
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