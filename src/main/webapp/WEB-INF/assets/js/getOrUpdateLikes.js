
function updateRating(answer_id, user_id, likeOrDislike, method)
{
    let url= "update-likes?user_id=" + user_id + "&answer_id=" +  answer_id + "&likeOrDislike=" + likeOrDislike;
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            console.log("request")
            return xmlhttp.responseText;
        }
    }
    xmlhttp.open(
        method,
        url,
        true);
    xmlhttp.send();
}
