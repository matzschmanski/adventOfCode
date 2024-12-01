const numbers = [2, 4, 6];
var sum =1;
sum = numbers.reduce(function summarize(sum, number) {
    
  const updatedSum = sum + number;
  console.log(updatedSum);
  return updatedSum;
}, 0);

console.log(sum); // 12