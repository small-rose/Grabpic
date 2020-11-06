package com.xiaocai.web.demo.entity.website;

import java.util.ArrayList;
import java.util.List;

public class WebLinkBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebLinkBaseExample() {
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

        public Criteria andCategryIdIsNull() {
            addCriterion("categry_id is null");
            return (Criteria) this;
        }

        public Criteria andCategryIdIsNotNull() {
            addCriterion("categry_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategryIdEqualTo(String value) {
            addCriterion("categry_id =", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdNotEqualTo(String value) {
            addCriterion("categry_id <>", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdGreaterThan(String value) {
            addCriterion("categry_id >", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdGreaterThanOrEqualTo(String value) {
            addCriterion("categry_id >=", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdLessThan(String value) {
            addCriterion("categry_id <", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdLessThanOrEqualTo(String value) {
            addCriterion("categry_id <=", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdLike(String value) {
            addCriterion("categry_id like", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdNotLike(String value) {
            addCriterion("categry_id not like", value, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdIn(List<String> values) {
            addCriterion("categry_id in", values, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdNotIn(List<String> values) {
            addCriterion("categry_id not in", values, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdBetween(String value1, String value2) {
            addCriterion("categry_id between", value1, value2, "categryId");
            return (Criteria) this;
        }

        public Criteria andCategryIdNotBetween(String value1, String value2) {
            addCriterion("categry_id not between", value1, value2, "categryId");
            return (Criteria) this;
        }

        public Criteria andLinkNoIsNull() {
            addCriterion("link_no is null");
            return (Criteria) this;
        }

        public Criteria andLinkNoIsNotNull() {
            addCriterion("link_no is not null");
            return (Criteria) this;
        }

        public Criteria andLinkNoEqualTo(Long value) {
            addCriterion("link_no =", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotEqualTo(Long value) {
            addCriterion("link_no <>", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoGreaterThan(Long value) {
            addCriterion("link_no >", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoGreaterThanOrEqualTo(Long value) {
            addCriterion("link_no >=", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoLessThan(Long value) {
            addCriterion("link_no <", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoLessThanOrEqualTo(Long value) {
            addCriterion("link_no <=", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoIn(List<Long> values) {
            addCriterion("link_no in", values, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotIn(List<Long> values) {
            addCriterion("link_no not in", values, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoBetween(Long value1, Long value2) {
            addCriterion("link_no between", value1, value2, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotBetween(Long value1, Long value2) {
            addCriterion("link_no not between", value1, value2, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkHerfIsNull() {
            addCriterion("link_herf is null");
            return (Criteria) this;
        }

        public Criteria andLinkHerfIsNotNull() {
            addCriterion("link_herf is not null");
            return (Criteria) this;
        }

        public Criteria andLinkHerfEqualTo(String value) {
            addCriterion("link_herf =", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfNotEqualTo(String value) {
            addCriterion("link_herf <>", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfGreaterThan(String value) {
            addCriterion("link_herf >", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfGreaterThanOrEqualTo(String value) {
            addCriterion("link_herf >=", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfLessThan(String value) {
            addCriterion("link_herf <", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfLessThanOrEqualTo(String value) {
            addCriterion("link_herf <=", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfLike(String value) {
            addCriterion("link_herf like", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfNotLike(String value) {
            addCriterion("link_herf not like", value, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfIn(List<String> values) {
            addCriterion("link_herf in", values, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfNotIn(List<String> values) {
            addCriterion("link_herf not in", values, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfBetween(String value1, String value2) {
            addCriterion("link_herf between", value1, value2, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkHerfNotBetween(String value1, String value2) {
            addCriterion("link_herf not between", value1, value2, "linkHerf");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNull() {
            addCriterion("link_name is null");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNotNull() {
            addCriterion("link_name is not null");
            return (Criteria) this;
        }

        public Criteria andLinkNameEqualTo(String value) {
            addCriterion("link_name =", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotEqualTo(String value) {
            addCriterion("link_name <>", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThan(String value) {
            addCriterion("link_name >", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThanOrEqualTo(String value) {
            addCriterion("link_name >=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThan(String value) {
            addCriterion("link_name <", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThanOrEqualTo(String value) {
            addCriterion("link_name <=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLike(String value) {
            addCriterion("link_name like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotLike(String value) {
            addCriterion("link_name not like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameIn(List<String> values) {
            addCriterion("link_name in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotIn(List<String> values) {
            addCriterion("link_name not in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameBetween(String value1, String value2) {
            addCriterion("link_name between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotBetween(String value1, String value2) {
            addCriterion("link_name not between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkValidIsNull() {
            addCriterion("link_valid is null");
            return (Criteria) this;
        }

        public Criteria andLinkValidIsNotNull() {
            addCriterion("link_valid is not null");
            return (Criteria) this;
        }

        public Criteria andLinkValidEqualTo(String value) {
            addCriterion("link_valid =", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidNotEqualTo(String value) {
            addCriterion("link_valid <>", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidGreaterThan(String value) {
            addCriterion("link_valid >", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidGreaterThanOrEqualTo(String value) {
            addCriterion("link_valid >=", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidLessThan(String value) {
            addCriterion("link_valid <", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidLessThanOrEqualTo(String value) {
            addCriterion("link_valid <=", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidLike(String value) {
            addCriterion("link_valid like", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidNotLike(String value) {
            addCriterion("link_valid not like", value, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidIn(List<String> values) {
            addCriterion("link_valid in", values, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidNotIn(List<String> values) {
            addCriterion("link_valid not in", values, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidBetween(String value1, String value2) {
            addCriterion("link_valid between", value1, value2, "linkValid");
            return (Criteria) this;
        }

        public Criteria andLinkValidNotBetween(String value1, String value2) {
            addCriterion("link_valid not between", value1, value2, "linkValid");
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
        
        public Criteria andLinkMarkEqualTo(String value) {
            addCriterion("link_mark =", value, "linkMark");
            return (Criteria) this;
        }

        public Criteria andLinkMarkNotEqualTo(String value) {
            addCriterion("link_mark <>", value, "linkMark");
            return (Criteria) this;
        }
        
        public Criteria andPageIdIsNull() {
            addCriterion("page_id is null");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNotNull() {
            addCriterion("page_id is not null");
            return (Criteria) this;
        }

        public Criteria andPageIdEqualTo(String value) {
            addCriterion("page_id =", value, "pageId");
            return (Criteria) this;
        }
        
        public Criteria andPageIdWithPageEqualTo(String value) {
            addCriterion("l.page_id =", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotEqualTo(String value) {
            addCriterion("page_id <>", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThan(String value) {
            addCriterion("page_id >", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThanOrEqualTo(String value) {
            addCriterion("page_id >=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThan(String value) {
            addCriterion("page_id <", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThanOrEqualTo(String value) {
            addCriterion("page_id <=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLike(String value) {
            addCriterion("page_id like", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotLike(String value) {
            addCriterion("page_id not like", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdIn(List<String> values) {
            addCriterion("page_id in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotIn(List<String> values) {
            addCriterion("page_id not in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdBetween(String value1, String value2) {
            addCriterion("page_id between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotBetween(String value1, String value2) {
            addCriterion("page_id not between", value1, value2, "pageId");
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