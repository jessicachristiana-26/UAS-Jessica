-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Bulan Mei 2026 pada 16.35
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `registrasi_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nim` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  `asal_sekolah` varchar(100) NOT NULL,
  `status` varchar(20) DEFAULT 'Pending',
  `prodi_id` bigint(20) DEFAULT NULL,
  `program_studi_id` bigint(20) DEFAULT NULL,
  `tanggal_daftar` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nama`, `nim`, `email`, `no_hp`, `tanggal_lahir`, `alamat`, `asal_sekolah`, `status`, `prodi_id`, `program_studi_id`, `tanggal_daftar`) VALUES
(1, 'Jessica Christiana', '0420230009', 'jessicachristiana26@gmail.com', '085722267594', '2005-01-26', 'dworowati, semarang ', 'SMK Negeri 1', 'Pending', 3, NULL, '2026-05-06'),
(2, 'Jonatan', '0420230010', 'Jonatan@gmail.com', '0838747394', '2004-02-02', 'cikarang barat 21', 'SMA 1', 'Rejected', 8, NULL, '2026-05-06'),
(3, 'Reynald', '0420230011', 'rey23@gmail.com', '0857739475', '2004-04-03', 'Jakarta Timur 3', 'SMK 3', 'Verified', 4, NULL, '2026-05-06'),
(4, 'jejen', '0420230012', 'jejen@gmail.com', '08570000000', '2005-03-02', 'jakarta selatan', 'SMA 2', 'Pending', 5, NULL, '2026-05-06'),
(5, 'danieka', '0420230013', 'deka23@gmail.com', '0852256743', '2004-04-23', 'blitar, jawa timur', 'SMK 4', 'Verified', 5, NULL, '2026-05-06'),
(6, 'andreas', '0420230014', 'andreas@gmail.com', '08522495932', '2005-04-03', 'cikarang utara 15', 'SMA', 'Pending', 2, NULL, '2026-05-06'),
(7, 'julior', '0420230015', 'julior@gmail.com', '0853980434', '2005-06-05', 'cikarang, jawa barat', 'SMA 5', 'Pending', 7, NULL, '2026-05-06');

-- --------------------------------------------------------

--
-- Struktur dari tabel `program_studi`
--

CREATE TABLE `program_studi` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `program_studi`
--

INSERT INTO `program_studi` (`id`, `nama`) VALUES
(1, 'Informatika'),
(2, 'Sistem Informasi'),
(3, 'Teknik Komputer'),
(4, 'Manajemen'),
(5, 'Akuntansi'),
(6, 'Informatika'),
(7, 'Informatika'),
(8, 'Sistem Informasi');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nim` (`nim`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `no_hp` (`no_hp`),
  ADD KEY `fk_prodi` (`prodi_id`),
  ADD KEY `FKnp2rn0cyo4eh0rgyt97jcs93o` (`program_studi_id`);

--
-- Indeks untuk tabel `program_studi`
--
ALTER TABLE `program_studi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `program_studi`
--
ALTER TABLE `program_studi`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `FKnp2rn0cyo4eh0rgyt97jcs93o` FOREIGN KEY (`program_studi_id`) REFERENCES `program_studi` (`id`),
  ADD CONSTRAINT `fk_prodi` FOREIGN KEY (`prodi_id`) REFERENCES `program_studi` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
