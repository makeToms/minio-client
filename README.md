# minio-client
minio文件服务器整合spring与spring boot
> 源码打包在pom.xml文中加入
>
> ```xml
> <build>
>     <plugins>
>         <!--生成doc jar包-->
>         <plugin>
>             <groupId>org.apache.maven.plugins</groupId>
>             <artifactId>maven-javadoc-plugin</artifactId>
>             <executions>
>                 <execution>
>                     <id>attach-javadocs</id>
>                     <goals>
>                         <goal>jar</goal>
>                     </goals>
>                     <!-- 不让像@Param 这种后面没写值的东西 报错。-->
>                     <configuration>
>                         <additionalJOption>-Xdoclint:none</additionalJOption>
>                     </configuration>
> 
>                 </execution>
>             </executions>
>         </plugin>
> 
>         <!--生成源码jar包-->
>         <plugin>
>             <groupId>org.apache.maven.plugins</groupId>
>             <artifactId>maven-source-plugin</artifactId>
>             <executions>
>                 <execution>
>                     <id>attach-sources</id>
>                     <goals>
>                         <goal>jar</goal>
>                     </goals>
>                 </execution>
>             </executions>
>         </plugin>
>     </plugins>
> </build>
> ```
>
> ```xml
> <properties>
>     <maven.test.skip>true</maven.test.skip>
>     <maven.javadoc.skip>true</maven.javadoc.skip>
> </properties>
> ```
>
> 