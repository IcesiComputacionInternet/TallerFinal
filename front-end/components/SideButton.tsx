import { NextPage } from "next";

interface Props {
    name:string;
    path:string;
    icon:any;
}

export default function SideButton({name,path}:Props) {
    return (
        <div style={{cursor:"pointer"}}>
            <h1>SideButton</h1>
        </div>
    );
}