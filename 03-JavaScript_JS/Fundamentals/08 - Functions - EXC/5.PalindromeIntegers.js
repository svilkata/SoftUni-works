function palindrome(arrInput = []) {

    function checkIfPalindrome(arrString) {
        let isPalindrome = true; 

        for (let i = 0; i < Math.trunc(arrString.length / 2); i++) {
            if (arrString[i] !== arrString [arrString.length - i - 1]) {
                isPalindrome = false;
                break;
            }
        }

        return isPalindrome;
    }

    for (let i = 0; i < arrInput.length; i++) {
        var res = checkIfPalindrome(arrInput[i] + '');
        console.log(res);        
    }  

}

palindrome([123,323,421,121]);