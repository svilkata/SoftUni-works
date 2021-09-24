function pie(pies = [], startPie, endPie) {
    const start = pies.indexOf(startPie);
    const end = pies.indexOf(endPie);

    const result = pies.slice(start, end + 1);

    return result;
}

pie(['Pumpkin Pie','Key Lime Pie','Cherry Pie','Lemon Meringue Pie','Sugar Cream Pie'],
'Key Lime Pie',
'Lemon Meringue Pie'
);