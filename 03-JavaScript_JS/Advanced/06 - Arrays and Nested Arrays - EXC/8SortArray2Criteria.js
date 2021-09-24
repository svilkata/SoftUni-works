function sort2Criteria(arr = []) {
    arr.sort ((f, s) => {
        let result = f.length - s.length;
        if (result === 0) {
            result = f.localeCompare(s);
        }
        return result;
    });


    for (let i = 0; i < arr.length; i++) {
        console.log(arr[i]);        
    }
}


sort2Criteria(['test',
    'Deny',
    'omen',
    'Default']
);
