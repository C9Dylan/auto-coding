package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.Id;

/**
 * @Author: yangyubing
 * @Description:
 * @Date: 16:14 2019/8/15
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "${tableName}")
@ApiModel
public class ${className} {

<#list fieldList as field>
<#if field.id?? && field.id == true>@Id@GeneratedValue(strategy = GenerationType.IDENTITY)</#if>
<#if field.comment?? && field.comment != "">@ApiModelProperty("${(field.comment)}")</#if>
<#if field.comment?? && field.comment != "" && field.id == false>@Column(name = "${(field.column)}")</#if>
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