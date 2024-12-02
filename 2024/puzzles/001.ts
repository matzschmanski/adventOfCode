import {readTxtFileLines} from '../snippets/read_input';




export const main = (): string => {
    var input = readTxtFileLines("001_test.txt");

    // split the line and put left side into array 1 and right side into array 2
    var arr1 = [];
    var arr2 = [];
    for (var i = 0; i < input.length; i++) {
        var temp = input[i].split("   ");
        //push and convert to number
        arr1.push(Number(temp[0]));
        arr2.push(Number(temp[1]));
        
    }
    // sort the arrays by lowest number to highest
    arr1.sort(function(a, b){return a-b});
    arr2.sort(function(a, b){return a-b});
    console.log(arr1);
    console.log(arr2);

    // create third array to store the difference between the two arrays
    var arr3 = [];
    for (var i = 0; i < arr1.length; i++) {
        arr3.push(arr2[i] - arr1[i]);
    }
    // ensure difference is always positive
    for (var i = 0; i < arr3.length; i++) {
        arr3[i] = Math.abs(arr3[i]);
    }
    console.log(arr3);
    //sum up the array
    var sum = 0;
    for (var i = 0; i < arr3.length; i++) {
        sum += arr3[i];
    }
    return sum.toString();
;}



export const main2 = (): string => {
    var input = readTxtFileLines("001_1.txt");
    var arr1 = [];
    var arr2 = [];
    for (var i = 0; i < input.length; i++) {
        var temp = input[i].split("   ");
        //push and convert to number
        arr1.push(Number(temp[0]));
        arr2.push(Number(temp[1]));
        
    }

    // create third array
    var arr3 = [];
    // Initialize arr3 with zeros
    for (var i = 0; i <= Math.max(...arr2); i++) {
        arr3[i] = 0;
    }

    // Count amount appearance of numbers in arr2 and store in arr3 sorted (pos 0 = amount of 0, pos 1 = amount of 1...)
    for (var i = 0; i < arr2.length; i++) {
        arr3[arr2[i]]++;
    }
    console.log(arr3);
    // iterate over arr1 and multiply number with arr3[number] and sum up
    var sum = 0;
    for (var i = 0; i < arr1.length; i++) {
        sum += arr1[i] * arr3[arr1[i]];
    }
    return sum.toString();
}

console.log(main2());
