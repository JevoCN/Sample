�ο���JPA ��ע�ο� http://www.oracle.com/technetwork/cn/middleware/ias/toplink-jpa-annotations-100895-zhs.html#IndexOfAnnotations


http://www.blogjava.net/sxyx2008/archive/2010/07/20/326601.html
1��@Entity(name="EntityName")

����,nameΪ��ѡ,��Ӧ���ݿ���һ�ĸ���



2��@Table(name="",catalog="",schema="")

��ѡ,ͨ����@Entity���ʹ��,ֻ�ܱ�ע��ʵ���class���崦,��ʾʵ���Ӧ�����ݿ�����Ϣ

name:��ѡ,��ʾ�������.Ĭ�ϵ�,������ʵ������һ��,ֻ���ڲ�һ�µ�����²���Ҫָ������

catalog:��ѡ,��ʾCatalog����,Ĭ��ΪCatalog("").

schema:��ѡ,��ʾSchema����,Ĭ��ΪSchema("").



3��@id

����

@id������ӳ�䵽���ݿ�������������,һ��ʵ��ֻ����һ�����Ա�ӳ��Ϊ����.����getXxxx()ǰ.



4��@GeneratedValue(strategy=GenerationType,generator="")

��ѡ

strategy:��ʾ�������ɲ���,��AUTO,INDENTITY,SEQUENCE �� TABLE 4��,�ֱ��ʾ��ORM����Զ�ѡ��,

�������ݿ��Identity�ֶ�����,�������ݿ���Sequence�ֶ�����,���и���һ������ı���������,Ĭ��ΪAUTO

generator:��ʾ����������������,�������ͨ����ORM������,����,Hibernate����ָ��uuid���������ɷ�ʽ.

ʾ��:

    @Id

    @GeneratedValues(strategy=StrategyType.SEQUENCE)

    public int getPk() {

       return pk;

    }



5��@Basic(fetch=FetchType,optional=true)

��ѡ

@Basic��ʾһ���򵥵����Ե����ݿ����ֶε�ӳ��,����û���κα�ע��getXxxx()����,Ĭ�ϼ�Ϊ@Basic

fetch: ��ʾ�����ԵĶ�ȡ����,��EAGER��LAZY����,�ֱ��ʾ��֧ץȡ���ӳټ���,Ĭ��ΪEAGER.

optional:��ʾ�������Ƿ�����Ϊnull,Ĭ��Ϊtrue

ʾ��:

    @Basic(optional=false)

    public String getAddress() {

       return address;

    }



6��@Column

��ѡ

@Column���������ݿ���и��ֶε���ϸ����,����ڸ���JPAע���������ݿ��ṹ�Ĺ��߷ǳ�������.

name:��ʾ���ݿ���и��ֶε�����,Ĭ��������������һ��

nullable:��ʾ���ֶ��Ƿ�����Ϊnull,Ĭ��Ϊtrue

unique:��ʾ���ֶ��Ƿ���Ψһ��ʶ,Ĭ��Ϊfalse

length:��ʾ���ֶεĴ�С,����String���͵��ֶ���Ч

insertable:��ʾ��ORM���ִ�в������ʱ,���ֶ��Ƿ�Ӧ����INSETRT�����,Ĭ��Ϊtrue

updateable:��ʾ��ORM���ִ�и��²���ʱ,���ֶ��Ƿ�Ӧ�ó�����UPDATE�����,Ĭ��Ϊtrue.����һ�������Ͳ����Ը��ĵ��ֶ�,�����Էǳ�����,�����birthday�ֶ�.

columnDefinition:��ʾ���ֶ������ݿ��е�ʵ������.ͨ��ORM��ܿ��Ը������������Զ��ж����ݿ����ֶε�����,���Ƕ���Date�������޷�ȷ�����ݿ����ֶ����;�����DATE,TIME����TIMESTAMP.����,String��Ĭ��ӳ������ΪVARCHAR,���Ҫ��String����ӳ�䵽�ض����ݿ��BLOB��TEXT�ֶ�����,�����Էǳ�����.

ʾ��:

    @Column(name="BIRTH",nullable="false",columnDefinition="DATE")

    public String getBithday() {

       return birthday;

    }



7��@Transient

��ѡ

@Transient��ʾ�����Բ���һ�������ݿ����ֶε�ӳ��,ORM��ܽ����Ը�����.

���һ�����Բ������ݿ����ֶ�ӳ��,����ؽ����ʾΪ@Transient,����,ORM���Ĭ����ע��Ϊ@Basic

ʾ��:

    //����birth�����age����

    @Transient

    public int getAge() {

       return getYear(new Date()) - getYear(birth);

    }



8��@ManyToOne(fetch=FetchType,cascade=CascadeType)

��ѡ

@ManyToOne��ʾһ�����һ��ӳ��,��ע���ע������ͨ�������ݿ������

optional:�Ƿ�������ֶ�Ϊnull,������Ӧ�ø������ݿ������Լ����ȷ��,Ĭ��Ϊtrue

fetch:��ʾץȡ����,Ĭ��ΪFetchType.EAGER

cascade:��ʾĬ�ϵļ�����������,����ָ��ΪALL,PERSIST,MERGE,REFRESH��REMOVE�е��������,Ĭ��Ϊ�޼�������

targetEntity:��ʾ�����Թ�����ʵ������.������ͨ������ָ��,ORM��ܸ������������Զ��ж�targetEntity.

ʾ��:

    //����Order���û�User��һ��ManyToOne�Ĺ�ϵ

    //��Order���ж���

    @ManyToOne()

    @JoinColumn(name="USER")

    public User getUser() {

       return user;

    }



9��@JoinColumn

��ѡ

@JoinColumn��@Column����,���������Ĳ���һ�����ֶ�,��һһ�������ֶ�,����.����һ��@ManyToOne���ֶ�.

name:���ֶε�����.����@JoinColumn��������һ�������ֶ�,��ManyToOne,��Ĭ�ϵ��������������ʵ�����.

����,ʵ��Order��һ��user����������ʵ��User,��Order��user����Ϊһ�����,

��Ĭ�ϵ�����Ϊʵ��User������+�»���+ʵ��User����������

ʾ��:

    ��@ManyToOne



10��@OneToMany(fetch=FetchType,cascade=CascadeType)

��ѡ

@OneToMany����һ��һ�Զ�Ĺ���,������Ӧ��Ϊ��������,�����ݿ��в�û��ʵ���ֶ�.

fetch:��ʾץȡ����,Ĭ��ΪFetchType.LAZY,��Ϊ�����Ķ������ͨ�����ش����ݿ�Ԥ�ȶ�ȡ���ڴ�

cascade:��ʾ������������,����OneToMany���͵Ĺ����ǳ���Ҫ,ͨ����ʵ����»�ɾ��ʱ,�������ʵ��ҲӦ�������»�ɾ��

����:ʵ��User��Order��OneToMany�Ĺ�ϵ,��ʵ��User��ɾ��ʱ,�������ʵ��OrderҲӦ�ñ�ȫ��ɾ��

ʾ��:

    @OneTyMany(cascade=ALL)

    public List getOrders() {

       return orders;

    }



11��@OneToOne(fetch=FetchType,cascade=CascadeType)

��ѡ

@OneToOne����һ��һ��һ�Ĺ���

fetch:��ʾץȡ����,Ĭ��ΪFetchType.LAZY

cascade:��ʾ������������

ʾ��:

    @OneToOne(fetch=FetchType.LAZY)

    public Blog getBlog() {

       return blog;

    }



12��@ManyToMany

��ѡ

@ManyToMany ����һ����Զ�Ĺ���.��Զ������������һ�Զ����,������ManyToMany������,�м������ORM����Զ�����

targetEntity:��ʾ��Զ��������һ��ʵ�����ȫ��,����:package.Book.class

mappedBy:��ʾ��Զ��������һ��ʵ����Ķ�Ӧ������������

ʾ��:

    Userʵ���ʾ�û�,Bookʵ���ʾ�鼮,Ϊ�������û��ղص��鼮,������User��Book֮�佨��ManyToMany����

    @Entity

    public class User {

       private List books;

       @ManyToMany(targetEntity=package.Book.class)

       public List getBooks() {

           return books;

       }

       public void setBooks(List books) {

           this.books=books;

       }

    }



    @Entity

    public class Book {

       private List users;

       @ManyToMany(targetEntity=package.Users.class, mappedBy="books")

       public List getUsers() {

           return users;

       }

       public void setUsers(List users) {

           this.users=users;

       }

    }

����ʵ����໥���������Ա�����Ϊ@ManyToMany,���໥ָ��targetEntity����,

��Ҫע�����,����ֻ��һ��ʵ���@ManyToManyע����Ҫָ��mappedBy����,ָ��targetEntity�ļ�����������

����ORM�����Զ����ɵı����User��Book����,���Զ�������һ��User_Book��,����ʵ�ֶ�Զ����



13��@MappedSuperclass

��ѡ

@MappedSuperclass���Խ������JPAע�⴫�ݸ�����,ʹ�����ܹ��̳г����JPAע��

ʾ��:

    @MappedSuperclass

    public class Employee() {

       ....

    }



    @Entity

    public class Engineer extends Employee {

       .....

    }

    @Entity

    public class Manager extends Employee {

       .....

    }



14��@Embedded

��ѡ

@Embedded�������ֶ���ϳ�һ����,����Ϊ����Entity��һ������.

����User����id,name,city,street,zip����.

����ϣ��city,street,zip����ӳ��ΪAddress����.����,User���󽫾���id,name��address����������.

Address������붨��Ϊ@Embededable

ʾ��:

    @Embeddable

    public class Address {city,street,zip}

    @Entity

    public class User {

       @Embedded

       public Address getAddress() {

           ..........

       }

    }



Hibernate��֤ע��

ע��

 ��������

 ˵��

 ʾ��



@Pattern

 String

 ͨ��������ʽ����֤�ַ���

 @attern(regex=��[a-z]{6}��)



@Length

 String

 ��֤�ַ����ĳ���

 @length(min=3,max=20)



@Email

 String

 ��֤һ��Email��ַ�Ƿ���Ч

 @email



@Range

 Long

 ��֤һ�������Ƿ�����Ч�ķ�Χ��

 @Range(min=0,max=100)



@Min

 Long

 ��֤һ�����ͱ��벻С��ָ��ֵ

 @Min(value=10)



@Max

 Long

 ��֤һ�����ͱ��벻����ָ��ֵ

 @Max(value=20)



@Size

 ���ϻ�����

 ���ϻ�����Ĵ�С�Ƿ���ָ����Χ��

 @Size(min=1,max=255)



����ÿ��ע�ⶼ��������һ��message���ԣ���������֤ʧ�ܺ����û����ص���Ϣ������������������ʹ�ö��ע��

