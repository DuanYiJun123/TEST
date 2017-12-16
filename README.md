# 一个测试框架的设计思想和使用说明by YiJun.Duan

</br>
## 1、背景
我自从转战互联网行业以来没多久，开始从软件测试工程师做起，平时需要测试比较多的业务就是通过发送http的post请求，通过填写对应的参数调用相应的接口来测试该接口的返回值和业务逻辑是否正确,刚开始会写一些简单的程序来实现发送请求这一简单的功能，但是随着时间的推移，发现要测试的接口越来越多，测试用例越来越多，参数越来越复杂，而且随着自己JAVA编程水平的提高，就希望能够设计出一套比较好的测试框架，来让我的工作变得越来越简单，减少大量重复的工作，于是这套测试框架便应运而生。

## 2、业务流程
我所从事的人脸识别行业，会在算法SDK的基础上，做成拥有后台管理、可以支持高并发，分布式部署的人脸识别引擎，这些引擎在处理识别结果和数据的时候底层自然离不开Mysql或者Oracle等的数据库的支持，所以自然会有诸如对人脸的图片的增删改查，数据底库的增删改查，1比1图片比对或者1比n图片比对这样的业务逻辑的接口，然而这些接口常常是某几个相关联的接口需要一起调用，比如我们要先创建好底库，再对底库进行人脸的增删改查，这样的调用很多，如果使用以前简单的方法进行每个接口单独调用，是一件非常麻烦的事情，而且不利于测试工程师设计测试用例。而这套测试框架解决这样的问题，我将每一个接口看作一个测试点(TestCase)，而多个测试点可以链接成一个测试链(TestChain),多个测试链又可以作为一个测试组(TestGroup)，这样我们不仅在调用接口的时候更加方便，而且在管理一些相关的接口方面，可以做到条理更加的清晰明朗，为测试工程师的测试工作更好的提供了便利与测试效率。

## 3、使用说明
这套框架目前使用的对象面向有一些JAVA基础的测试工程师。首先，我们要在qqduan.test.cases这个包下面创建我们要测试的接口的类，一般我们统一命名为XXXTestCase,如果你的这个类也是同样需要发送http的post请求的话，那么请继承qqduan.test.core包下的AbsHttpTestCase类，然后我们需要在你新创建的类名上方打上HttpTest的标签，该标签需要填写上要post的url地址以及端口号（我已经为我常用的几个写成了枚举类的形式，有需要的话可以自定义），同样的，如果你要进行测试的内容不是通过http的post请求的话，你可以自定义一个测试类并继承AbsTestCase类来重写你的test方法，以实现自定义的测试方法。然后我们需要在qqduan.test.process包下面新建一个类，一般我们统一命名为ProcessXXX,并继承AbsTestProcess类，这时我们需要重写两个方法，一个是tmplates()方法，用于设置好该Process的测试组里面的测试链是如何构成的，一个是setData()方法，用于用户根据自己设置的templates和测试用例来进行测试数据的填写，关于具体如何实现这两个方法，请自行参考代码示例。如果需要测试流程的前后置事件，还可以在该类
implement一些前后置事件的接口，例如GroupBefore,GroupAfter,ChainBefore,ChainAfter等接口，就可以实现每次执行测试组或者测试链的时候，进行一些前后置事件的运行，例如我们可以在后置事件中，把测试时添加的人脸图片进行删除，以降低测试行为对被测对象的改变和“污染”降到最低。在写完这个类之后，我们可以在onfig.xml的配置文件中配置对应的Ip和测试日志写出的线程数，然后在process标签里面配置上我们刚刚写的Process类的包路径，并写上skip和logType，skip是跳过该项测试与否，logType是配置日志打印的类型，包括全部打印，成功时打印和失败时打印，做这个的目的是因为有的时候大数据量的测试会让日志打印过多或一些不必要看的日志会增加我们定位测试问题的难度。配置写好之后，我们就可以在App的main函数直接运行即可。

## 4、设计思想
这套测试框架包含了反射，自定义标签等语法和AOP面向切面编程的设计模式，含有大量的多态思想和进行了函数的回调，希望能给测试工程师带来便利和效率提升。

## 5、联系方式
Mr. YiJun Duan</br>
Email-377028119@qq.com</br>
有任何问题，欢迎来邮件交流咨询！
