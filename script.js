const birthdateInput = document.getElementById('birthdate');
const basedateInput = document.getElementById('basedate');
const calculateButton = document.getElementById('calculate');
const ageOutput = document.getElementById('age');

calculateButton.addEventListener('click', () => {
  const birthdate = birthdateInput.value;
  const basedate = basedateInput.value;

  // 日付のバリデーション
  // 2025/04/25 UPDATE
  if (!isValidDate(birthdate) || !isValidDate(basedate)) {
    ageOutput.textContent = "日付の形式が不正です。";
    return;
  }

  // 年齢計算
  const age = calculateAge(birthdate, basedate);
  ageOutput.textContent = `年齢: ${age}歳`;
});

function isValidDate(dateString) {
  const regex = /^\d{4}\/\d{2}\/\d{2}$/;
  return regex.test(dateString);
}

function calculateAge(birthdate, basedate) {
  const birthDateParts = birthdate.split('/');
  const baseDateParts = basedate.split('/');

  const birthYear = parseInt(birthDateParts[0]);
  const birthMonth = parseInt(birthDateParts[1]) - 1; // 月は0から始まる
  const birthDay = parseInt(birthDateParts[2]);

  const baseYear = parseInt(baseDateParts[0]);
  const baseMonth = parseInt(baseDateParts[1]) - 1;
  const baseDay = parseInt(baseDateParts[2]);

  const birthDate = new Date(birthYear, birthMonth, birthDay);
  const baseDate = new Date(baseYear, baseMonth, baseDay);

  let age = baseYear - birthYear;

  // 基準日の月日が生年月日の月日より小さい場合は、年齢を1歳減らす
  if (baseMonth < birthMonth || (baseMonth === birthMonth && baseDay < birthDay)) {
    age--;
  }

  return age;
}