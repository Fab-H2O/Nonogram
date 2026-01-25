export function displayElement(id)
{
    console.log(id);
    const element = document.querySelector(id)
    element.style.display = "block";
}
export function closeElement(id)
{
    console.log(id);
    const element = document.querySelector(id)
    element.style.display = "none";
}