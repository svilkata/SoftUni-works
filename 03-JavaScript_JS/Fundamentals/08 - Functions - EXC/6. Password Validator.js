function passwordValidator(inputString) {

    function isValidLength(inputString) {
        if (inputString.length < 6 || inputString.length > 10) {
            console.log('Password must be between 6 and 10 characters');
            return false;
        } 
        return true;
    }

    function checkOnlyLettersAndDigits(inputString) {
        for (let i = 0; i < inputString.length; i++) {
            if ((inputString[i].charCodeAt(0) >= 65 && inputString[i].charCodeAt(0) <= 90) || 
            (inputString[i].charCodeAt(0) >= 97 && inputString[i].charCodeAt(0) <= 122) ||
            (inputString[i].charCodeAt(0) >= 48 && inputString[i].charCodeAt(0) <= 57)) {
                
            } else {
                console.log('Password must consist only of letters and digits');
                return false;
            }            
        }
        
        return true;
    }

    function is2Digits(inputString) {
        let countDigits = 0;

        for (let i = 0; i < inputString.length; i++) {
            if (inputString[i].charCodeAt(0) >= 48 && inputString[i].charCodeAt(0) <= 57) {
                countDigits++;
                if (countDigits >= 2) {
                    return true;
                }
            }         
        }

        console.log('Password must have at least 2 digits');
        return false;
    }

    let boolResult = true; 
    boolResult = isValidLength(inputString) && boolResult; 
    boolResult = checkOnlyLettersAndDigits(inputString) && boolResult
    boolResult = is2Digits(inputString) && boolResult;

    if (boolResult) {
        console.log('Password is valid');
    }
}

passwordValidator('logIn');
// passwordValidator('MyPass123');