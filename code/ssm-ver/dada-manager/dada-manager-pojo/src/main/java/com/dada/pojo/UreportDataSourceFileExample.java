package com.dada.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UreportDataSourceFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UreportDataSourceFileExample() {
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

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(String value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(String value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(String value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(String value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(String value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(String value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLike(String value) {
            addCriterion("file_id like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotLike(String value) {
            addCriterion("file_id not like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<String> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<String> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(String value1, String value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(String value1, String value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("category_id like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("category_id not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andFileDescrIsNull() {
            addCriterion("file_descr is null");
            return (Criteria) this;
        }

        public Criteria andFileDescrIsNotNull() {
            addCriterion("file_descr is not null");
            return (Criteria) this;
        }

        public Criteria andFileDescrEqualTo(String value) {
            addCriterion("file_descr =", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrNotEqualTo(String value) {
            addCriterion("file_descr <>", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrGreaterThan(String value) {
            addCriterion("file_descr >", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrGreaterThanOrEqualTo(String value) {
            addCriterion("file_descr >=", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrLessThan(String value) {
            addCriterion("file_descr <", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrLessThanOrEqualTo(String value) {
            addCriterion("file_descr <=", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrLike(String value) {
            addCriterion("file_descr like", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrNotLike(String value) {
            addCriterion("file_descr not like", value, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrIn(List<String> values) {
            addCriterion("file_descr in", values, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrNotIn(List<String> values) {
            addCriterion("file_descr not in", values, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrBetween(String value1, String value2) {
            addCriterion("file_descr between", value1, value2, "fileDescr");
            return (Criteria) this;
        }

        public Criteria andFileDescrNotBetween(String value1, String value2) {
            addCriterion("file_descr not between", value1, value2, "fileDescr");
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

        public Criteria andPublicStateIsNull() {
            addCriterion("public_state is null");
            return (Criteria) this;
        }

        public Criteria andPublicStateIsNotNull() {
            addCriterion("public_state is not null");
            return (Criteria) this;
        }

        public Criteria andPublicStateEqualTo(String value) {
            addCriterion("public_state =", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateNotEqualTo(String value) {
            addCriterion("public_state <>", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateGreaterThan(String value) {
            addCriterion("public_state >", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateGreaterThanOrEqualTo(String value) {
            addCriterion("public_state >=", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateLessThan(String value) {
            addCriterion("public_state <", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateLessThanOrEqualTo(String value) {
            addCriterion("public_state <=", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateLike(String value) {
            addCriterion("public_state like", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateNotLike(String value) {
            addCriterion("public_state not like", value, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateIn(List<String> values) {
            addCriterion("public_state in", values, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateNotIn(List<String> values) {
            addCriterion("public_state not in", values, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateBetween(String value1, String value2) {
            addCriterion("public_state between", value1, value2, "publicState");
            return (Criteria) this;
        }

        public Criteria andPublicStateNotBetween(String value1, String value2) {
            addCriterion("public_state not between", value1, value2, "publicState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateIsNull() {
            addCriterion("visible_state is null");
            return (Criteria) this;
        }

        public Criteria andVisibleStateIsNotNull() {
            addCriterion("visible_state is not null");
            return (Criteria) this;
        }

        public Criteria andVisibleStateEqualTo(String value) {
            addCriterion("visible_state =", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateNotEqualTo(String value) {
            addCriterion("visible_state <>", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateGreaterThan(String value) {
            addCriterion("visible_state >", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateGreaterThanOrEqualTo(String value) {
            addCriterion("visible_state >=", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateLessThan(String value) {
            addCriterion("visible_state <", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateLessThanOrEqualTo(String value) {
            addCriterion("visible_state <=", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateLike(String value) {
            addCriterion("visible_state like", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateNotLike(String value) {
            addCriterion("visible_state not like", value, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateIn(List<String> values) {
            addCriterion("visible_state in", values, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateNotIn(List<String> values) {
            addCriterion("visible_state not in", values, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateBetween(String value1, String value2) {
            addCriterion("visible_state between", value1, value2, "visibleState");
            return (Criteria) this;
        }

        public Criteria andVisibleStateNotBetween(String value1, String value2) {
            addCriterion("visible_state not between", value1, value2, "visibleState");
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