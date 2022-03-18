package com.dada.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataDictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataDictionaryExample() {
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

        public Criteria andDataIdIsNull() {
            addCriterion("data_id is null");
            return (Criteria) this;
        }

        public Criteria andDataIdIsNotNull() {
            addCriterion("data_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataIdEqualTo(String value) {
            addCriterion("data_id =", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotEqualTo(String value) {
            addCriterion("data_id <>", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThan(String value) {
            addCriterion("data_id >", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThanOrEqualTo(String value) {
            addCriterion("data_id >=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThan(String value) {
            addCriterion("data_id <", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThanOrEqualTo(String value) {
            addCriterion("data_id <=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLike(String value) {
            addCriterion("data_id like", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotLike(String value) {
            addCriterion("data_id not like", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdIn(List<String> values) {
            addCriterion("data_id in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotIn(List<String> values) {
            addCriterion("data_id not in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdBetween(String value1, String value2) {
            addCriterion("data_id between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotBetween(String value1, String value2) {
            addCriterion("data_id not between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataCodeIsNull() {
            addCriterion("data_code is null");
            return (Criteria) this;
        }

        public Criteria andDataCodeIsNotNull() {
            addCriterion("data_code is not null");
            return (Criteria) this;
        }

        public Criteria andDataCodeEqualTo(String value) {
            addCriterion("data_code =", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotEqualTo(String value) {
            addCriterion("data_code <>", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeGreaterThan(String value) {
            addCriterion("data_code >", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeGreaterThanOrEqualTo(String value) {
            addCriterion("data_code >=", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLessThan(String value) {
            addCriterion("data_code <", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLessThanOrEqualTo(String value) {
            addCriterion("data_code <=", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLike(String value) {
            addCriterion("data_code like", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotLike(String value) {
            addCriterion("data_code not like", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeIn(List<String> values) {
            addCriterion("data_code in", values, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotIn(List<String> values) {
            addCriterion("data_code not in", values, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeBetween(String value1, String value2) {
            addCriterion("data_code between", value1, value2, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotBetween(String value1, String value2) {
            addCriterion("data_code not between", value1, value2, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNull() {
            addCriterion("data_name is null");
            return (Criteria) this;
        }

        public Criteria andDataNameIsNotNull() {
            addCriterion("data_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataNameEqualTo(String value) {
            addCriterion("data_name =", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotEqualTo(String value) {
            addCriterion("data_name <>", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThan(String value) {
            addCriterion("data_name >", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_name >=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThan(String value) {
            addCriterion("data_name <", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLessThanOrEqualTo(String value) {
            addCriterion("data_name <=", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameLike(String value) {
            addCriterion("data_name like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotLike(String value) {
            addCriterion("data_name not like", value, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameIn(List<String> values) {
            addCriterion("data_name in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotIn(List<String> values) {
            addCriterion("data_name not in", values, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameBetween(String value1, String value2) {
            addCriterion("data_name between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataNameNotBetween(String value1, String value2) {
            addCriterion("data_name not between", value1, value2, "dataName");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("data_type like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("data_type not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNull() {
            addCriterion("data_status is null");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNotNull() {
            addCriterion("data_status is not null");
            return (Criteria) this;
        }

        public Criteria andDataStatusEqualTo(String value) {
            addCriterion("data_status =", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotEqualTo(String value) {
            addCriterion("data_status <>", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThan(String value) {
            addCriterion("data_status >", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThanOrEqualTo(String value) {
            addCriterion("data_status >=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThan(String value) {
            addCriterion("data_status <", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThanOrEqualTo(String value) {
            addCriterion("data_status <=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLike(String value) {
            addCriterion("data_status like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotLike(String value) {
            addCriterion("data_status not like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusIn(List<String> values) {
            addCriterion("data_status in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotIn(List<String> values) {
            addCriterion("data_status not in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusBetween(String value1, String value2) {
            addCriterion("data_status between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotBetween(String value1, String value2) {
            addCriterion("data_status not between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andIsleafIsNull() {
            addCriterion("isLeaf is null");
            return (Criteria) this;
        }

        public Criteria andIsleafIsNotNull() {
            addCriterion("isLeaf is not null");
            return (Criteria) this;
        }

        public Criteria andIsleafEqualTo(String value) {
            addCriterion("isLeaf =", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotEqualTo(String value) {
            addCriterion("isLeaf <>", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafGreaterThan(String value) {
            addCriterion("isLeaf >", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafGreaterThanOrEqualTo(String value) {
            addCriterion("isLeaf >=", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLessThan(String value) {
            addCriterion("isLeaf <", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLessThanOrEqualTo(String value) {
            addCriterion("isLeaf <=", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLike(String value) {
            addCriterion("isLeaf like", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotLike(String value) {
            addCriterion("isLeaf not like", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafIn(List<String> values) {
            addCriterion("isLeaf in", values, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotIn(List<String> values) {
            addCriterion("isLeaf not in", values, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafBetween(String value1, String value2) {
            addCriterion("isLeaf between", value1, value2, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotBetween(String value1, String value2) {
            addCriterion("isLeaf not between", value1, value2, "isleaf");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
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