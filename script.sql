Use Master;
Drop Database Laptop;
Create Database Laptop;
Use Laptop;

create table Laptop(
	id int identity primary key,
	[name] nvarchar(4000),	
	processor nvarchar(4000),
	ramSize nvarchar(4000),
    [disk] nvarchar(4000),
	graphicProcessor nvarchar(4000),
	point float,
);

Create Table Detail(
	id int identity primary key,
	laptopID int not null,
	source nvarchar(50),
	price nvarchar(50),
    brand nvarchar(50),
    img nvarchar(4000),
    href nvarchar(4000),
    color nvarchar(4000),
    model nvarchar(4000),
    dvd nvarchar(4000),
    cache nvarchar(4000),
    screenSize nvarchar(4000),
    touchScreen nvarchar(4000),
    audio nvarchar(4000),
    lan nvarchar(4000),
    wl nvarchar(4000),
    bluetooth nvarchar(4000),
    webcam nvarchar(4000),
    fingerPrint nvarchar(4000),
    [port] nvarchar(4000),
    keyboard nvarchar(4000),
    pin nvarchar(4000),
    [weight] nvarchar(2000),
    size nvarchar(2000),
    accessories nvarchar(4000),
    other nvarchar(4000),
    os nvarchar(4000),
    guarantee nvarchar(4000),
    ship nvarchar(4000),
    origin nvarchar(4000),
    gift nvarchar(4000),
    [status] nvarchar(4000),
);

create table Popular(
	id int identity primary key,
	laptopid int not null,
	[view] int not null
)
ALTER TABLE Detail
ADD CONSTRAINT U_Detail UNIQUE (ID,laptopID);
-- Create Foreign key
ALTER TABLE Detail ADD CONSTRAINT FK_Detail
FOREIGN KEY(laptopID) REFERENCES Laptop(id);
--
ALTER TABLE Popular ADD CONSTRAINT FK_View
FOREIGN KEY(laptopID) REFERENCES Laptop(id);
-- Create Unique key
create table Cpu(
	id int identity primary key,
	name nvarchar(4000) not null,
	point int not null,
	rank int not null
);

create table Gpu(
	id int identity primary key,
	name nvarchar(4000) not null,
	point int not null,
	rank int not null
);

select * from laptop
select * from detail
select * from Cpu
select * from gpu
select * from Popular