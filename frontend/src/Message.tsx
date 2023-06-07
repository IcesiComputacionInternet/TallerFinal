interface Props{
    message: string;
}

function Message({ message }: Props){
    return <h1>{message}</h1>;
}

export default Message;