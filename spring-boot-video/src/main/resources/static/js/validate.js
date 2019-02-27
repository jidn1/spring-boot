/**
 * 验证密码
 * @param str
 * @returns
 */
function check_password (str) {
    if(str!=undefined){
        if(str.length>=8){
            return /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d]{8,12}$/.test(str);
        }
    }
    return false;
}
/**
 * 邮箱验证
 * @param str
 * @returns
 */
function check_email (str) {
    if(str!=undefined){
        if(str.length>=6){
            return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str);
        }
    }
    return false;
}