use epamphoto

create table TUser
(
id uniqueidentifier primary key not null,
userLogin nvarchar(60) not null,
userPassword nvarchar(max) not null,
isDeleted bit not null
)
create table TDataPhoto
(
id uniqueidentifier primary key not null,
dataimage varbinary not null,
imghash nvarchar(max) not null
)
create table TCategory
(
id uniqueidentifier primary key not null,
categoryName nvarchar(60) not null,
creator uniqueidentifier references TUser(id),
)
create table TPhoto
(
id uniqueidentifier primary key not null,
imgname nvarchar(max) not null,
img uniqueidentifier references TDataPhoto(id) not null,
uploader uniqueidentifier references TUser(id) not null
)

create table TCategoryFromPhoto
(
photoID uniqueidentifier references TPhoto(id),
categoryID uniqueidentifier references TCategory(id),
primary key(photoID, categoryID)
)
go

create procedure CreateUser
	@id uniqueidentifier,
	@login nvarchar(60),
	@password nvarchar(max)
as
	insert into TUser values (@id, @login, @password, 0)
go

create procedure SighIn
	@login nvarchar(60),
	@passwod nvarchar(max)
as
	select id, userLogin from TUser where userLogin = @login and userPassword = @passwod
go

create procedure FindUserByID
	@id uniqueidentifier
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
	@id uniqueidentifier
as
	update TUser set isDeleted = 1 where id = @id
go

create procedure RestoreUser
	@id uniqueidentifier
as
	update TUser set isDeleted = 0 where id = @id
go

create procedure CreateCategory
	@id uniqueidentifier,
	@nameCategory nvarchar(60),
	@creator uniqueidentifier
as
	insert into TCategory values (@id, @nameCategory, @creator)
go

create procedure DeleteCategory
	@id uniqueidentifier
as
	delete from TCategory where id = @id
go

create procedure EditCategory
	@id uniqueidentifier,
	@newName nvarchar(60)
as
	update TCategory set categoryName = @newName where id = @id
go

create procedure FindCategoryByID
	@id uniqueidentifier
as
	select * from TCategory where id = @id
go

create procedure FindPersonalCategoryByName
	@name nvarchar(60),
	@id uniqueidentifier
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
	@id uniqueidentifier,
	@bytes varbinary(max),
	@hash nvarchar(max)
as
	insert into TDataPhoto values (@id, @bytes, @hash)
go

create procedure FindByHash
	@hash nvarchar(max)
as
	select id from TDataPhoto where @hash = imghash
go

create procedure AddPhoto
	@id uniqueidentifier,
	@nameimg nvarchar(max),
	@idimg uniqueidentifier,
	@idcreator uniqueidentifier
as
	insert into TPhoto values (@id, @nameimg, @idimg, @idcreator)
go

create procedure DeletePhoto
	@id uniqueidentifier
as
	delete from TPhoto where id = @id
go

create procedure FindPhotoByID
	@id uniqueidentifier
as
	select * from TPhoto where id = @id
go

create procedure GetPhotoFromUser
	@id uniqueidentifier
as
	select * from TPhoto where uploader = @id
go

create procedure GetAllPhotos
as
	select * from TPhoto