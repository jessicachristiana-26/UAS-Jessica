function validateForm() {

    let valid = true;

    let nama = document.getElementById("nama").value;
    if (nama.length < 4) {
        document.getElementById("namaError").innerText = "Nama minimal 4 karakter";
        valid = false;
    }

    let nim = document.getElementById("nim").value;
    if (nim.length != 10 || isNaN(nim)) {
        document.getElementById("nimError").innerText = "NIM harus 10 digit angka";
        valid = false;
    }

    let hp = document.getElementById("noHp").value;
    if (!(hp.startsWith("08") || hp.startsWith("62"))) {
        document.getElementById("hpError").innerText = "Format HP salah";
        valid = false;
    }

    let alamat = document.getElementById("alamat").value;
    if (alamat.length < 15) {
        document.getElementById("alamatError").innerText = "Alamat minimal 15 karakter";
        valid = false;
    }

    return valid;
}