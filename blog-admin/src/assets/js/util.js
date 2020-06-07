export function getDicValue(key,array,valueKey) {
    let obj = array.find(el => el.id==key)
    return obj[valueKey];
}

export function formatDate(timestamp) {
    let date = new Date((''+timestamp).length===13?timestamp:timestamp*1000);
    let year = date.getFullYear();
    let month = date.getMonth()<10?'0'+date.getMonth():date.getMonth();
    let day = date.getDay()<10?'0'+date.getDay():date.getDay();
    return year+"/"+month+"/"+day;
}