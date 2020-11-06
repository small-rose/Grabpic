package com.xiaocai.web.demo.entity.pic;

import java.util.ArrayList;
import java.util.List;

public class PicUrlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PicUrlExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAddrNameIsNull() {
            addCriterion("addr_name is null");
            return (Criteria) this;
        }

        public Criteria andAddrNameIsNotNull() {
            addCriterion("addr_name is not null");
            return (Criteria) this;
        }

        public Criteria andAddrNameEqualTo(String value) {
            addCriterion("addr_name =", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameNotEqualTo(String value) {
            addCriterion("addr_name <>", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameGreaterThan(String value) {
            addCriterion("addr_name >", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameGreaterThanOrEqualTo(String value) {
            addCriterion("addr_name >=", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameLessThan(String value) {
            addCriterion("addr_name <", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameLessThanOrEqualTo(String value) {
            addCriterion("addr_name <=", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameLike(String value) {
            addCriterion("addr_name like", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameNotLike(String value) {
            addCriterion("addr_name not like", value, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameIn(List<String> values) {
            addCriterion("addr_name in", values, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameNotIn(List<String> values) {
            addCriterion("addr_name not in", values, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameBetween(String value1, String value2) {
            addCriterion("addr_name between", value1, value2, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrNameNotBetween(String value1, String value2) {
            addCriterion("addr_name not between", value1, value2, "addrName");
            return (Criteria) this;
        }

        public Criteria andAddrDescIsNull() {
            addCriterion("addr_desc is null");
            return (Criteria) this;
        }

        public Criteria andAddrDescIsNotNull() {
            addCriterion("addr_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAddrDescEqualTo(String value) {
            addCriterion("addr_desc =", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescNotEqualTo(String value) {
            addCriterion("addr_desc <>", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescGreaterThan(String value) {
            addCriterion("addr_desc >", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescGreaterThanOrEqualTo(String value) {
            addCriterion("addr_desc >=", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescLessThan(String value) {
            addCriterion("addr_desc <", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescLessThanOrEqualTo(String value) {
            addCriterion("addr_desc <=", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescLike(String value) {
            addCriterion("addr_desc like", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescNotLike(String value) {
            addCriterion("addr_desc not like", value, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescIn(List<String> values) {
            addCriterion("addr_desc in", values, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescNotIn(List<String> values) {
            addCriterion("addr_desc not in", values, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescBetween(String value1, String value2) {
            addCriterion("addr_desc between", value1, value2, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andAddrDescNotBetween(String value1, String value2) {
            addCriterion("addr_desc not between", value1, value2, "addrDesc");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(String value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(String value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(String value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(String value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(String value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(String value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLike(String value) {
            addCriterion("is_del like", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotLike(String value) {
            addCriterion("is_del not like", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<String> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<String> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(String value1, String value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(String value1, String value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsMarkIsNull() {
            addCriterion("is_mark is null");
            return (Criteria) this;
        }

        public Criteria andIsMarkIsNotNull() {
            addCriterion("is_mark is not null");
            return (Criteria) this;
        }

        public Criteria andIsMarkEqualTo(String value) {
            addCriterion("is_mark =", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotEqualTo(String value) {
            addCriterion("is_mark <>", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkGreaterThan(String value) {
            addCriterion("is_mark >", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkGreaterThanOrEqualTo(String value) {
            addCriterion("is_mark >=", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLessThan(String value) {
            addCriterion("is_mark <", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLessThanOrEqualTo(String value) {
            addCriterion("is_mark <=", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkLike(String value) {
            addCriterion("is_mark like", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotLike(String value) {
            addCriterion("is_mark not like", value, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkIn(List<String> values) {
            addCriterion("is_mark in", values, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotIn(List<String> values) {
            addCriterion("is_mark not in", values, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkBetween(String value1, String value2) {
            addCriterion("is_mark between", value1, value2, "isMark");
            return (Criteria) this;
        }

        public Criteria andIsMarkNotBetween(String value1, String value2) {
            addCriterion("is_mark not between", value1, value2, "isMark");
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