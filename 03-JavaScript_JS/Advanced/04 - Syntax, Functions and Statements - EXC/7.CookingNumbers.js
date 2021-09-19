function cookingByNumbers(num, op1, op2, op3, op4, op5) {
    function chop(nnn) {
        return nnn / 2;
    };

    function dice(nn) {
        return Math.sqrt(nn);
    };

    function spice(nn) {
        return nn + 1;
    };

    function bake(nn) {
        return nn * 3;
    };

    function fillet(nn) {
        return nn * 0.8;
    };

    let result = Number(num);
    const arr = [op1, op2, op3, op4, op5];
    for (let i = 0; i < arr.length; i++) {
        switch (arr[i]) {
            case 'chop':
                result = chop(result);
                break;
            case 'dice':
                result = dice(result);
                break;
            case 'spice':
                result = spice(result);
                break;
            case 'bake':
                result = bake(result);
                break;
            case 'fillet':
                result = fillet(result);
                break;
        }

        console.log(result);
    }
}

cookingByNumbers('32', 'chop', 'chop', 'chop', 'chop', 'chop');
cookingByNumbers('9', 'dice', 'spice', 'chop', 'bake', 'fillet');