


export function renderTemplate(templateAsString) {
    //по принцип с regex не се parse-ва html текст
    const pattern = /{{(.+?)}}/gm;

    return (data) => {
        return templateAsString.replace(pattern, (match, propName) => {
            return data[propName];
            // console.log(propName, data[propName]);
        });
    }
    





}