function displayElement(id)
{
    const element = document.querySelector(id)
    element.style.display = "flex";
}
function closeElement(id)
{
    const element = document.querySelector(id)
    element.style.display = "none";
}