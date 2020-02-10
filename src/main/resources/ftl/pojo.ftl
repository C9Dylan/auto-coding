package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

/**
 * Created by Kim QQ.Cong on ${.now?string["yyyy-MM-dd"]} / ${.now?string["HH:mm:ss"]}
 *
 * @author: CongQingquan
 * @Description: 事项类型枚举
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "${tableName}")
@ApiModel
public class ${className} {

<#list fieldList as field>
 <#-- 1. id -->
  <#if field.id?? && field.id == true>
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
  </#if>
  <#-- 2. api model property -->
  <#if field.comment?? && field.comment != "">
    @ApiModelProperty("${(field.comment)}")
  </#if>
  <#-- 3. column -->
  <#if field.comment?? && field.comment != "" && field.id == false>
    @Column(name = "${(field.column)}")
  </#if>
  <#-- 4. date -->
  <#if field.type?? && field.type != "" && field.type == "Date">
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  </#if>
	private ${(field.type)!""} ${(field.name)!""};

</#list>
<#--
<#list fieldList as field>
    <#if field.comment?? && field.comment != "">
    /**
     * ${(field.comment)}
     */
    </#if>
	public ${(field.type)!""} get${(field.name)?cap_first!""}() {
        return ${(field.name)!""};
	}

    <#if field.comment?? && field.comment != "">
    /**
     * ${(field.comment)}
     */
    </#if>
	public void set${(field.name)?cap_first!""}(${(field.type)!""} ${(field.name)!""}) {
        this.${(field.name)!""} = ${(field.name)!""};
	}

</#list>

-->
}