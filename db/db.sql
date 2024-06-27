use master
go

create database pm_ban_ao_phong_by_amity
go

use pm_ban_ao_phong_by_amity
go

CREATE TABLE [thiet_ke] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [san_pham] (
  [id] uniqueidentifier,
  [ma_san_pham] varchar(10),
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [nguoi_dung] (
  [id] uniqueidentifier,
  [ma_nguoi_dung] varchar(20),
  [mat_khau] varchar(20),
  [ho_ten] nvarchar(50),
  [ngay_sinh] date,
  [gioi_tinh] bit,
  [hinh_anh] varchar(50),
  [diem_thuong] int,
  [so_dien_thoai] varchar(20),
  [email] varchar(50),
  [dia_chi] nvarchar(255),
  [vai_tro] bit,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [thong_bao_nguoi_dung] (
  [id] uniqueidentifier,
  [id_tai_khoan] uniqueidentifier,
  [noi_dung] nvarchar(255),
  [so_dien_thoai] varchar(20),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_thong_bao_nguoi_dung.id_tai_khoan]
    FOREIGN KEY ([id_tai_khoan])
      REFERENCES [nguoi_dung]([id])
);

CREATE TABLE [khach_hang] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [so_dien_thoai] varchar(20),
  [dia_chi] nvarchar(255),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [kich_thuoc] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [thuong_hieu] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [chat_lieu] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [co_ao] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [nha_san_xuat] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [phong_cach] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [the_loai] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [mau_sac] (
  [id] uniqueidentifier,
  [ten] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [chi_tiet_san_pham] (
  [id] uniqueidentifier,
  [id_san_pham] uniqueidentifier,
  [id_thuong_hieu] uniqueidentifier,
  [id_mau_sac] uniqueidentifier,
  [id_the_loai] uniqueidentifier,
  [id_chat_lieu] uniqueidentifier,
  [id_kich_thuoc] uniqueidentifier,
  [id_nha_san_xuat] uniqueidentifier,
  [thiet_ke] uniqueidentifier,
  [phong_cach] uniqueidentifier,
  [co_ao] uniqueidentifier,
  [so_luong_ton] int,
  [gia_nhap] float,
  [gia_ban] float,
  [ngay_nhap_kho] date default getdate(),
  [lan_sua_cuoi] date default getdate(),
  [mo_ta] nvarchar(255),
  [nguoi_tao] varchar(50),
  [nguoi_sua] varchar(50),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_thuong_hieu]
    FOREIGN KEY ([id_thuong_hieu])
      REFERENCES [thuong_hieu]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_chat_lieu]
    FOREIGN KEY ([id_chat_lieu])
      REFERENCES [chat_lieu]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_kich_thuoc]
    FOREIGN KEY ([id_kich_thuoc])
      REFERENCES [kich_thuoc]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.co_ao]
    FOREIGN KEY ([co_ao])
      REFERENCES [co_ao]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_nha_san_xuat]
    FOREIGN KEY ([id_nha_san_xuat])
      REFERENCES [nha_san_xuat]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.phong_cach]
    FOREIGN KEY ([phong_cach])
      REFERENCES [phong_cach]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.thiet_ke]
    FOREIGN KEY ([thiet_ke])
      REFERENCES [thiet_ke]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_the_loai]
    FOREIGN KEY ([id_the_loai])
      REFERENCES [the_loai]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_san_pham]
    FOREIGN KEY ([id_san_pham])
      REFERENCES [san_pham]([id]),
  CONSTRAINT [FK_chi_tiet_san_pham.id_mau_sac]
    FOREIGN KEY ([id_mau_sac])
      REFERENCES [mau_sac]([id])
);

CREATE TABLE [hoa_don] (
  [id] uniqueidentifier,
  [id_nguoi_tao] uniqueidentifier,
  [id_khach_hang] uniqueidentifier,
  [ten_khach_hang] nvarchar(50),
  [so_dien_thoai] varchar(20),
  [dia_chi] nvarchar(255),
  [tong_tien] float,
  [ngay_xac_nhan] date default getdate(),
  [ngay_van_chuyen] date default getdate(),
  [ngay_nhan] date default getdate(),
  [ghi_chu] nvarchar(255),
  [tien_van_chuyen] float,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_hoa_don.id_nguoi_tao]
    FOREIGN KEY ([id_nguoi_tao])
      REFERENCES [nguoi_dung]([id]),
  CONSTRAINT [FK_hoa_don.id_khach_hang]
    FOREIGN KEY ([id_khach_hang])
      REFERENCES [khach_hang]([id])
);

CREATE TABLE [hd_ctsp] (
  [id] uniqueidentifier,
  [id_ctsp] uniqueidentifier,
  [id_hoa_don] uniqueidentifier,
  [so_luong] int,
  [gia_tien] float,
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_hd_ctsp.id_ctsp]
    FOREIGN KEY ([id_ctsp])
      REFERENCES [chi_tiet_san_pham]([id]),
  CONSTRAINT [FK_hd_ctsp.id_hoa_don]
    FOREIGN KEY ([id_hoa_don])
      REFERENCES [hoa_don]([id])
);

CREATE TABLE [voucher] (
  [id] uniqueidentifier,
  [ma_voucher] varchar(50),
  [ten] nvarchar(50),
  [gia_tri_phan_tram] int,
  [gia_tri_tien] float,
  [so_luong] int,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [ngay_bat_dau] date,
  [ngay_ket_thuc] date,
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [voucher_hoa_don] (
  [id] uniqueidentifier,
  [id_voucher] uniqueidentifier,
  [id_hoa_don] uniqueidentifier,
  [gia_cu] float,
  [gia_moi] float,
  [giam_gia] float,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [ngay_bat_dau] date,
  [ngay_ket_thuc] date,
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_voucher_hoa_don.id_voucher]
    FOREIGN KEY ([id_voucher])
      REFERENCES [voucher]([id]),
  CONSTRAINT [FK_voucher_hoa_don.id_hoa_don]
    FOREIGN KEY ([id_hoa_don])
      REFERENCES [hoa_don]([id])
);

CREATE TABLE [thong_bao_khach_hang] (
  [id] uniqueidentifier,
  [id_khach_hang] uniqueidentifier,
  [noi_dung] nvarchar(255),
  [so_dien_thoai] varchar(20),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_thong_bao_khach_hang.id_khach_hang]
    FOREIGN KEY ([id_khach_hang])
      REFERENCES [khach_hang]([id])
);

CREATE TABLE [gio_hang] (
  [id] uniqueidentifier,
  [id_nguoi_dung] uniqueidentifier,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_gio_hang.id_nguoi_dung]
    FOREIGN KEY ([id_nguoi_dung])
      REFERENCES [nguoi_dung]([id])
);

CREATE TABLE [gio_hang_ctsp] (
  [id] uniqueidentifier,
  [id_ctsp] uniqueidentifier,
  [id_gio_hang] uniqueidentifier,
  [so_luong] int,
  [gia] float,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_gio_hang_ctsp.id]
    FOREIGN KEY ([id])
      REFERENCES [chi_tiet_san_pham]([id]),
  CONSTRAINT [FK_gio_hang_ctsp.id_gio_hang]
    FOREIGN KEY ([id_gio_hang])
      REFERENCES [gio_hang]([id])
);

CREATE TABLE [khuyen_mai] (
  [id] uniqueidentifier,
  [ten_khuyen_mai] nvarchar(50),
  [gia_tri_phan_tram] int default 0,
  [gia_tri_tien] float default 0,
  [ngay_tao] date default getdate(),
  [lan_sua_cuoi] date default getdate(),
  [ngay_bat_dau] date,
  [ngay_ket_thuc] date,
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [km_ctsp] (
  [id] uniqueidentifier,
  [id_km] uniqueidentifier,
  [id_ctsp] uniqueidentifier,
  [gia_cu] float,
  [gia_moi] float,
  [giam_gia] float,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_km_ctsp.id_ctsp]
    FOREIGN KEY ([id_ctsp])
      REFERENCES [chi_tiet_san_pham]([id]),
  CONSTRAINT [FK_km_ctsp.id_km]
    FOREIGN KEY ([id_km])
      REFERENCES [khuyen_mai]([id])
);

CREATE TABLE [khach_hang_voucher] (
  [id] uniqueidentifier,
  [id_voucher] uniqueidentifier,
  [id_khach_hang] uniqueidentifier,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] bit,
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_khach_hang_voucher.id_khach_hang]
    FOREIGN KEY ([id_khach_hang])
      REFERENCES [khach_hang]([id]),
  CONSTRAINT [FK_khach_hang_voucher.id_voucher]
    FOREIGN KEY ([id_voucher])
      REFERENCES [voucher]([id])
);

CREATE TABLE [phuong_thuc_thanh_toan] (
  [id] uniqueidentifier,
  [phuong_thuc] nvarchar(50),
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  [deleted] bit,
  PRIMARY KEY ([id])
);

CREATE TABLE [hoa_don_pttt] (
  [id] uniqueidentifier,
  [id_hoa_don] uniqueidentifier,
  [id_pttt] uniqueidentifier,
  [so_tien_mat] float,
  [so_tien_ck] float,
  [tong_tien] float,
  [ngay_tao] date default getdate(),
  [ngay_sua] date default getdate(),
  [trang_thai] nvarchar(50),
  PRIMARY KEY ([id]),
  CONSTRAINT [FK_hoa_don_pttt.id_pttt]
    FOREIGN KEY ([id_pttt])
      REFERENCES [phuong_thuc_thanh_toan]([id]),
  CONSTRAINT [FK_hoa_don_pttt.id_hoa_don]
    FOREIGN KEY ([id_hoa_don])
      REFERENCES [hoa_don]([id])
);

