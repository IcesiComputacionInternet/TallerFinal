import { NextPage } from "next";
import  Router  from "next/router";

interface Props {
    name:string;
    path:string;
    icon:any;
    handleClick:any;
}

export default function SideButton({name,path,icon,handleClick}:Props) {
   
    return (
        <div style={{cursor:"pointer",display:"flex",justifyContent:"left",alignItems:"center",width:"calc(100%)"}}
        onClick={handleClick}>
            <div style={{paddingLeft:25}}></div>            
            {icon}
            <h2 style={{paddingLeft:20,fontWeight:"lighter",color:"#e6e6e9"}}>{name}</h2>
        </div>
    );
}