interface TopbarButtonProps {
    text: string;
    handleClick: any;
}

export default function TopbarButton({text,handleClick}:TopbarButtonProps) {
    return(
        <div style={{cursor:"pointer",display:"flex",justifyContent:"center",alignItems:"center",width:150}}
        onClick={handleClick}>
            <h2 style={{fontWeight:"lighter",color:"#e6e6e9"}}>{text}</h2>
        </div>
    )
}