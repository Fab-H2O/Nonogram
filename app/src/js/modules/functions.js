export function displayElement(id)
{
    const element = document.querySelector(id)
    element.style.display = "block";
}
export function closeElement(id)
{
    const element = document.querySelector(id)
    element.style.display = "none";
}