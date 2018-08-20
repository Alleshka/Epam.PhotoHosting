create database epamphoto
go

use epamphoto

create table TUser
(
id int primary key not null IDENTITY,
userLogin nvarchar(60) unique not null,
userPassword nvarchar(max) not null,
isDeleted bit not null
)
create table TDataPhoto
(
id int primary key not null IDENTITY,
dataimage varbinary(max) not null,
imghash nvarchar(max) not null
)
create table TCategory
(
id int primary key not null IDENTITY,
categoryName nvarchar(60) not null,
creator int references TUser(id),
)
create table TPhoto
(
id int primary key not null IDENTITY,
imgname nvarchar(max) not null,
img int references TDataPhoto(id) not null,
uploader int references TUser(id) not null
)

create table TCategoryFromPhoto
(
photoID int references TPhoto(id),
categoryID int references TCategory(id),
primary key(photoID, categoryID)
)
go

create procedure CreateUser
	@login nvarchar(60),
	@password nvarchar(max)
as
	insert into TUser values (@login, @password, 0)
	select id, userlogin from TUser where userLogin = @login
go

create procedure SighIn
	@login nvarchar(60),
	@passwod nvarchar(max)
as
	select id, userLogin from TUser where userLogin = @login and userPassword = @passwod
go

create procedure FindUserByID
	@id int
as
	select id, userLogin, isDeleted from TUser where id = @id
go

create procedure FindUserByLogin
	@login nvarchar(60)
as
	select id, userLogin, isDeleted from TUser where userLogin = @login
go

create procedure GetAllUsers
as
	select id, userLogin from TUser where isDeleted = 0
go

create procedure DeleteUser
	@id int
as
	update TUser set isDeleted = 1 where id = @id
go

create procedure RestoreUser
	@id int
as
	update TUser set isDeleted = 0 where id = @id
go

create procedure CreateCategory
	@nameCategory nvarchar(60),
	@creator int
as
	insert into TCategory values (@nameCategory, @creator)
go

create procedure DeleteCategory
	@id int
as
	delete from TCategory where id = @id
go

create procedure EditCategory
	@id int,
	@newName nvarchar(60)
as
	update TCategory set categoryName = @newName where id = @id
go

create procedure FindCategoryByID
	@id int
as
	select * from TCategory where id = @id
go

create procedure FindPersonalCategoryByName
	@name nvarchar(60),
	@id int
as
	select * from TCategory where categoryName = @name and creator = id
go

create procedure FindAllCategoryByName
	@name nvarchar(60)
as
	select * from TCategory where categoryName = @name
go

create procedure GetCategoryList
as
	select * from TCategory
go

create procedure AddImg
	@bytes varbinary(max),
	@hash nvarchar(max)
as
	insert into TDataPhoto values (@bytes, @hash)
go

create procedure FindByHash
	@hash nvarchar(max)
as
	select * from TDataPhoto where @hash = imghash
go

create procedure AddPhoto
	@nameimg nvarchar(max),
	@idimg int,
	@idcreator int
as
	insert into TPhoto values (@nameimg, @idimg, @idcreator)
go

create procedure DeletePhoto
	@id int
as
	delete from TCategoryFromPhoto where photoID = @id
	delete from TPhoto where id = @id
go

create procedure FindPhotoByID
	@id int
as
	select * from TPhoto where id = @id
go

create procedure GetPhotoFromUser
	@id int
as
	select * from TPhoto where uploader = @id
go

create procedure GetAllPhotos
as
	select id, img, uploader from TPhoto
go

create procedure GetPhotosFromCategory
	@id int
as
select * from TCategoryFromPhoto as TC join TPhoto as PH on TC.photoID = PH.id where TC.categoryID = @id
go

create procedure AddPhotoToCategory
	@photoID int,
	@categoryID int
as
insert into TCategoryFromPhoto values(@photoID, @categoryID)
go

create procedure RemovePhotoFromCategory
	@photoID int,
	@categoryID int
as
	delete from TCategoryFromPhoto where categoryID = @categoryID and photoID = @photoID
go
