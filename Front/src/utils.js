// Formats Java date strings to the dd/MM/yyyy format
export function formataData(data) {
    return data.split(' ')[0].split('-')[2] + '/' +     // Day
        data.split('-')[1] + '/' + data.split('-')[0];  // Month and Year
}