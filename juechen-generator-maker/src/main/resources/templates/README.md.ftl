# ${name}

> ${description}
>
> **作者**：${author}
>
> **基于**：[玦尘代码生成器项目](https://github.com/miahemu/juechen-generator)

> 非常感谢您的使用，欢迎到 GitHub 上给项目点个 Star ⭐，支持我们持续优化！

这个代码生成器支持通过命令行交互式输入方式，动态生成所需项目代码，让您轻松自定义代码生成过程。

---

## 🌟 快速开始

在项目根目录下执行以下命令，即可使用生成器：

```bash
generator <命令> <选项参数>
```

**示例命令：**

```bash
generator generate <#list modelConfig.models as modelInfo>-${modelInfo.abbr} </#list>
```

---

## 📖 参数说明

每个生成项的详细说明如下：

<#list modelConfig.models as modelInfo>
    ${modelInfo?index + 1} ${modelInfo.fieldName}

    - 类型：${modelInfo.type}
    - 描述：${modelInfo.description}
    - 默认值：${modelInfo.defaultValue?c}
    - 缩写：-${modelInfo.abbr}

</#list>

---

## ❤️ 支持我们

觉得这个项目对你有帮助？欢迎 [访问 GitHub 项目主页](https://github.com/miahemu/juechen-generator) 并点个 Star ⭐，帮助我们让更多开发者受益！